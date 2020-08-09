package br.com.casadocodigo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@Getter
public class PurchaseDto {

    private Long id;
    private CustomerDto customer;
    private BigDecimal totalPrice;
    private Set<PurchasedItemDto> purchasedItems;

}
