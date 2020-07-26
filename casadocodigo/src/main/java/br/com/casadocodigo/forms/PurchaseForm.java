package br.com.casadocodigo.forms;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

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

    public void toEntity(EntityManager entityManager) {
        if(Objects.isNull(this.stateId)) {
            Query query = entityManager.createQuery("SELECT s FROM State AS s WHERE Country.id = :countryId");
            query.setParameter("countryId", countryId);
            if(query.getResultList().size() > 0) throw new RuntimeException();

        }

    }

}
