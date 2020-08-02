package br.com.casadocodigo.constraints;

import br.com.casadocodigo.constraints.validators.CountryAndStateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CountryAndStateValidator.class)
public @interface CountryAndState {
    String message() default "State required";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
