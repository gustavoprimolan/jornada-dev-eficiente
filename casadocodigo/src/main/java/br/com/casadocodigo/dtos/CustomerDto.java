package br.com.casadocodigo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomerDto {

    private String email;
    private String name;
    private String lastName;
    private String cpfCnpj;
    private String address;
    private String complement;
    private String city;
    private String phone;
    private String cep;
    private CountryDto country;
    private StateDto state;



}
