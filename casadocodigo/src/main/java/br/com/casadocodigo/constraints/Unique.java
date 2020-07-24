package br.com.casadocodigo.constraints;

import br.com.casadocodigo.constraints.validators.UniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
public @interface Unique {

    String message() default "Must be unique";
    Class<?> entityClass();
    String entityField();
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
