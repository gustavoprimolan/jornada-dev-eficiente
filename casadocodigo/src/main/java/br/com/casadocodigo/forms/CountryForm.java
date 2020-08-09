package br.com.casadocodigo.forms;

import br.com.casadocodigo.constraints.Unique;
import br.com.casadocodigo.entities.Country;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
public class CountryForm {

    @Unique(entityClass = Country.class, entityField = "name")
    @NotNull @NotEmpty
    private String name;

    private CountryForm(){}

    public Country toEntity() {
        return new Country(this.name);
    }

}
