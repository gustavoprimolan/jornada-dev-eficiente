package br.com.casadocodigo.constraints.validators;

import br.com.casadocodigo.constraints.CountryAndState;
import br.com.casadocodigo.forms.PurchaseForm;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CountryAndStateValidator implements ConstraintValidator<CountryAndState, PurchaseForm> {

    private final EntityManager entityManager;

    public CountryAndStateValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean isValid(PurchaseForm purchase, ConstraintValidatorContext context) {
        if(!purchase.isStateIdNull()) return true;

        Query query = entityManager.createQuery("SELECT CASE WHEN (count(s) > 0) THEN true ELSE false END FROM State AS s WHERE s.country.id = : countryId AND s.id = :stateId");
        query.setParameter("countryId", purchase.getCountryId());
        query.setParameter("stateId", purchase.getStateId());
        Boolean areThereStates = (Boolean) query.getSingleResult();

        return !areThereStates;
    }

}
