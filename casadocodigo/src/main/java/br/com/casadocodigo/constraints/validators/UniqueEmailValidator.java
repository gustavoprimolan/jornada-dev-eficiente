package br.com.casadocodigo.constraints.validators;

import br.com.casadocodigo.constraints.UniqueEmail;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final EntityManager entityManager;

    public UniqueEmailValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.isNull(email)) return false;
        Query query = entityManager.createQuery("select a.email from Autor as a where a.email = :email");
        query.setParameter("email", email);
        return CollectionUtils.isEmpty(query.getResultList());
    }
}
