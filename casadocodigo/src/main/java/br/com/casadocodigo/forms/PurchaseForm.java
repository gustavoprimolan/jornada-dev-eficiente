package br.com.casadocodigo.forms;

import br.com.casadocodigo.constraints.ValidVoucher;
import br.com.casadocodigo.entities.Customer;
import br.com.casadocodigo.entities.Purchase;
import br.com.casadocodigo.entities.PurchasedItem;
import br.com.casadocodigo.entities.Voucher;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

//7 POINTS
@AllArgsConstructor
public class PurchaseForm {

    @Valid
    private CustomerForm customer;

    @ValidVoucher
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
        Voucher voucher = findVoucherCodeByCode(entityManager, voucherCode);
        List<Function<Purchase, PurchasedItem>> functionPurchasedItems = PurchaseItemForm.createConstructorFunctionEntityList(entityManager, purchaseItems);
        Purchase purchase = new Purchase(customer, totalPrice, functionPurchasedItems);
        purchase.applyVoucher(voucher);
        return purchase;
    }

    private Voucher findVoucherCodeByCode(EntityManager entityManager, String voucherCode) {
        Query query = entityManager.createQuery("SELECT v FROM Voucher v WHERE v.code = :voucherCode");
        query.setParameter("voucherCode", voucherCode);
        return (Voucher) query.getSingleResult();
    }

}
