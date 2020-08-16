package br.com.casadocodigo.forms;

import br.com.casadocodigo.constraints.Unique;
import br.com.casadocodigo.entities.Voucher;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
public class VoucherForm {

    @Unique(entityClass = Voucher.class, entityField = "code")
    @NotNull @NotBlank
    private String code;

    @Min(0) @Max(100) @NotNull
    private BigDecimal discountPercentage;

    @Future @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate expiryDate;

    public Voucher toEntity() {
        return new Voucher(this.code, this.discountPercentage, this.expiryDate);
    }

}
