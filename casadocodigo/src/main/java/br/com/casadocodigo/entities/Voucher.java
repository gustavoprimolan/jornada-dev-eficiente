package br.com.casadocodigo.entities;

import br.com.casadocodigo.dtos.VoucherDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Voucher")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "code", nullable = false, unique = true)
    public String code;

    @Min(0) @Max(100)
    @Column(name = "discount_percentage", nullable = false)
    public BigDecimal discountPercentage;

    @Future
    @Column(name = "expiry_date", nullable = false)
    public LocalDate expiryDate;

    /**      
     * @deprecated (it is not recommended build an empty object, but jpa needs this guy... =/)      
     * */
    @Deprecated
    public Voucher() {}

    public Voucher(String code, BigDecimal discountPercentage, LocalDate expiryDate) {
        this.code = code;
        this.discountPercentage = discountPercentage;
        this.expiryDate = expiryDate;
    }

    public static void validateExpiration(Voucher voucher) {
        if(Objects.isNull(voucher)) return;
        voucher.validateExpiration();
    }

    public VoucherDto toDto() {
        return new VoucherDto(this.id, this.code, this.discountPercentage, this.expiryDate);
    }

    public boolean isExpired() {
        return LocalDate.now().isBefore(this.expiryDate);
    }

    public void validateExpiration() {
        if(isExpired()) throw new IllegalArgumentException("Voucher.expiryDate.invalid");
    }

}
