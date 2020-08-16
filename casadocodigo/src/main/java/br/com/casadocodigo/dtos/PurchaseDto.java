package br.com.casadocodigo.dtos;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Getter
public class PurchaseDto {

    private Long id;
    private CustomerDto customer;
    private BigDecimal totalPrice;
    private Set<PurchasedItemDto> purchasedItems;
    private VoucherDto voucher;
    private BigDecimal totalPriceWithDiscount;

    public PurchaseDto(Long id, CustomerDto customer, BigDecimal totalPrice, Set<PurchasedItemDto> purchasedItems, VoucherDto voucher) {
        this.id = id;
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.purchasedItems = purchasedItems;
        setVoucherParams(voucher);
    }

    private void setVoucherParams(VoucherDto voucher) {
        if(Objects.isNull(voucher)) return;
        this.voucher = voucher;
        totalPriceWithDiscount = voucher.discountFromValue(totalPrice);
    }

}
