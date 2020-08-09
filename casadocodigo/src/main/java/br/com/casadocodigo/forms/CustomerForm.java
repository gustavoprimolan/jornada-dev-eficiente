package br.com.casadocodigo.forms;

import br.com.casadocodigo.constraints.CountryAndState;
import br.com.casadocodigo.entities.Customer;
import br.com.casadocodigo.entities.State;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@CountryAndState
@Setter
public class CustomerForm {

    @NotNull @NotBlank @Email
    private String email;

    @NotBlank @NotNull
    private String name;

    @NotNull @NotBlank
    private String lastName;

    @NotNull @NotBlank
    private String cpfCnpj;

    @NotNull @NotBlank
    private String address;

    @NotNull @NotBlank
    private String complement;

    @NotNull @NotBlank
    private String city;

    @NotNull @NotBlank
    private Long countryId;

    private Long stateId;

    @NotNull @NotBlank
    private String phone;

    @NotNull @NotBlank
    private String cep;

    protected Long getCountryId() {
        return this.countryId;
    }

    protected Long getStateId() {
        return this.stateId;
    }

    protected boolean isStateIdNull() {
        return Objects.isNull(this.stateId);
    }

    public Customer toEntity(EntityManager entityManager) {
        State state = entityManager.find(State.class, stateId);
        return new Customer(this.email, this.name, this.lastName,
                this.cpfCnpj, this.address, this.complement,
                this.city, this.phone, this.cep,
                state.getCountry(), state);
    }


}
