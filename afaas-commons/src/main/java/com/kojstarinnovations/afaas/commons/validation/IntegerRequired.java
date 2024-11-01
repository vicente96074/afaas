package com.kojstarinnovations.afaas.commons.validation;

import com.kojstarinnovations.afaas.commons.validators.IntegerRequiredValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IntegerRequiredValidator.class)
public @interface IntegerRequired {
    String message() default "This field must be an integer";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
