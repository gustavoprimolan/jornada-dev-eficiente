package br.com.casadocodigo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PurchasedItemDto {

    private BookDto book;
    private int quantity;

}
