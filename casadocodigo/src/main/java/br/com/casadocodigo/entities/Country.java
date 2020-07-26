package br.com.casadocodigo.entities;

import br.com.casadocodigo.dtos.CountryDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Deprecated
    protected Country(){}

    public Country(String name) {
        this.name = name;
    }

    public CountryDto toDto() {
        return new CountryDto(this.id, this.name);
    }

}
