package br.com.casadocodigo.entities;

import br.com.casadocodigo.dtos.PurchaseDto;
import br.com.casadocodigo.dtos.PurchasedItemDto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@Table(name = "Purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Customer customer;

    @Column(name="total_price", nullable = false)
    private BigDecimal totalPrice;


    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PurchasedItem> purchasedItems;

    /**      
     * @deprecated (it is not recommended build an empty object, but jpa needs this guy... =/)      
     * */
    @Deprecated
    public Purchase() {}

    public Purchase(Customer customer, BigDecimal totalPrice, List<Function<Purchase, PurchasedItem>> functionPurchasedItems) {
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.purchasedItems = functionPurchasedItems.stream().map(function -> function.apply(this)).collect(Collectors.toSet());
        BigDecimal totalPriceOfItems = this.purchasedItems.stream().map(PurchasedItem::sumItemPriceWithQuantity).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        if(!totalPriceOfItems.equals(this.totalPrice)) throw new IllegalArgumentException("Purchase.total.invalid");
    }

    public PurchaseDto toDto() {
        Set<PurchasedItemDto> purchasedItemsDto = this.purchasedItems.stream().map(PurchasedItem::toDto).collect(Collectors.toSet());
        return new PurchaseDto(this.id, this.customer.toDto(), this.totalPrice, purchasedItemsDto);
    }

}
