package com.kojstarinnovations.afaas.commons.validation;

import com.kojstarinnovations.afaas.commons.validators.IdIntegerRequiredValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * IdIntegerRequired annotation definition, this annotation is used to validate if a field is an integer
 *
 * @author Augusto Vicente
 */
@Documented
@Constraint(validatedBy = IdIntegerRequiredValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IdIntegerRequired {
    String message() default "This field must be an integer";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
