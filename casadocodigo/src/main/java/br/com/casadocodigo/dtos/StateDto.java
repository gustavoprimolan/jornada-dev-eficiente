package br.com.casadocodigo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StateDto {

    private Long id;
    private String name;
    private CountryDto country;

}
