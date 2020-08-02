package br.com.casadocodigo.forms;

import br.com.casadocodigo.constraints.CountryAndState;
import br.com.casadocodigo.entities.PurchasedItem;
import br.com.casadocodigo.entities.Purchase;
import br.com.casadocodigo.entities.State;
import br.com.casadocodigo.exceptions.InvalidCountryException;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@CountryAndState
public class PurchaseForm {

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

    @NotNull @Valid
    private List<ItemForm> items;

    @NotNull @Min(20)
    private BigDecimal totalPrice;

    public Long getCountryId() {
        return this.countryId;
    }

    public boolean isStateIdNull() {
        return Objects.isNull(this.stateId);
    }

    public Purchase toEntity(EntityManager entityManager) {
        State state = entityManager.find(State.class, stateId);

        if(!state.isCountryIdEquals(countryId)) throw new InvalidCountryException();

        List<Function<Purchase, PurchasedItem>> functionPurchasedItems = this.items.stream().map(item -> item.toEntity(entityManager)).collect(Collectors.toList());

        Purchase newPurchase = new Purchase(email, name, lastName, cpfCnpj, address, complement, city, phone, cep, totalPrice, state.getCountry(), state, functionPurchasedItems);

        newPurchase.validateTotalPriceWithTotalPriceOfBooks();

        return new Purchase(email, name, lastName, cpfCnpj, address, complement, city, phone, cep, totalPrice, state.getCountry(), state, functionPurchasedItems);
    }


}
