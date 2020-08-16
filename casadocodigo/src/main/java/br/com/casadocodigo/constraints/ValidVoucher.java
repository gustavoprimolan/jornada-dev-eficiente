package br.com.casadocodigo.constraints;

import br.com.casadocodigo.constraints.validators.ValidVoucherValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidVoucherValidator.class)
public @interface ValidVoucher {
    String message() default "Must be valid";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
