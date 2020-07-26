package br.com.casadocodigo.forms;

import br.com.casadocodigo.constraints.Unique;
import br.com.casadocodigo.entities.Country;
import br.com.casadocodigo.entities.State;
import br.com.casadocodigo.exceptions.ResourceNotFoundException;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;

@Setter
public class StateForm {

    @Unique(entityClass = State.class, entityField = "name")
    @NotNull @NotBlank
    private String name;
    @NotNull @Positive
    private Long countryId;

    private StateForm(){}

    public State toEntity(EntityManager entityManager) {
        Country country = Optional.ofNullable(entityManager.find(Country.class, countryId)).orElseThrow(() -> new ResourceNotFoundException("Country", countryId));
        return new State(this.name, country);
    }

}
