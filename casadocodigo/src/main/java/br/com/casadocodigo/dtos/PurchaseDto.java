package br.com.casadocodigo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@Getter
public class PurchaseDto {

    private Long id;
    private String email;
    private String name;
    private String lastName;
    private String cpfCnpj;
    private String address;
    private String complement;
    private String city;
    private String phone;
    private String cep;
    private BigDecimal totalPrice;
    private CountryDto country;
    private StateDto state;
    private Set<PurchasedItemDto> purchasedItems;

}
