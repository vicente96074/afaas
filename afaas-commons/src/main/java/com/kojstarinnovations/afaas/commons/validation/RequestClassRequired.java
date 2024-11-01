package com.kojstarinnovations.afaas.commons.validation;

import com.kojstarinnovations.afaas.commons.validators.RequestClassRequiredValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RequestClassRequiredValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestClassRequired {
    String message() default "Request class is required";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
