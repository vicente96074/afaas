package com.kojstarinnovations.afaas.commons.validation;

import com.kojstarinnovations.afaas.commons.validators.LocalDateRequiredValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LocalDateRequiredValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalDateRequired {

    String message() default "Fecha requerida";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
