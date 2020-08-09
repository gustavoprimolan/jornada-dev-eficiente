package br.com.casadocodigo.forms;

import br.com.casadocodigo.entities.Customer;
import br.com.casadocodigo.entities.Purchase;
import br.com.casadocodigo.entities.PurchasedItem;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Setter
public class PurchaseForm {

    @Valid
    private CustomerForm customer;

    private String voucherCode;

    @NotNull @Min(20)
    private BigDecimal totalPrice;

    @NotNull @Valid
    private List<PurchaseItemForm> purchaseItems;

    public Long getCountryId() {
        return this.customer.getCountryId();
    }

    public Object getStateId() {
        return this.customer.getStateId();
    }

    public boolean isStateIdNull() {
        return this.customer.isStateIdNull();
    }

    public Purchase toEntity(EntityManager entityManager) {
        Customer customer = this.customer.toEntity(entityManager);
        List<Function<Purchase, PurchasedItem>> functionPurchasedItems = this.purchaseItems.stream().map(item -> item.toEntity(entityManager)).collect(Collectors.toList());
        return new Purchase(customer, totalPrice, functionPurchasedItems);
    }


}
