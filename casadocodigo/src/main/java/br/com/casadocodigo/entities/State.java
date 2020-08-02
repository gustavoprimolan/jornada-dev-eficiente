package br.com.casadocodigo.entities;

import br.com.casadocodigo.dtos.StateDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "State")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
    private Country country;

    @Deprecated
    protected State() {}

    public State(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public StateDto toDto() {
        return new StateDto(this.id, this.name, this.country.toDto());
    }

    public boolean isCountryIdEquals(Long countryId) {
        return this.country.isIdEquals(countryId);
    }

    public Country getCountry() {
        return country;
    }
}
