package br.com.casadocodigo.constraints.validators;

import br.com.casadocodigo.constraints.Unique;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.stream.Stream;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    private final EntityManager entityManager;
    private Class<?> entityClass;
    private String entityField;

    public UniqueValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.entityClass = constraintAnnotation.entityClass();
        this.entityField = constraintAnnotation.entityField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.isNull(value)) return false;

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();

        Root<?> root = criteriaQuery.from(entityClass);
        Predicate predicate = criteriaBuilder.equal(root.get(entityField), value);
        criteriaQuery.select(root).where(predicate);

        Stream<?> resultAsStream = entityManager.createQuery(criteriaQuery).setMaxResults(1).getResultStream();

        return !resultAsStream.findFirst().isPresent();
    }

}
