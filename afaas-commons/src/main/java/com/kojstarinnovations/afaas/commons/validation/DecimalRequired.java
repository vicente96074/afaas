package com.kojstarinnovations.afaas.commons.validation;

import com.kojstarinnovations.afaas.commons.validators.DecimalRequiredValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DecimalRequiredValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DecimalRequired {
    String message() default "This field is required";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
