package com.kojstarinnovations.afaas.commons.validation;

import com.kojstarinnovations.afaas.commons.validators.DataRequiredValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * DataRequired annotation definition, this annotation is used to validate if a field is required
 *
 * @author Augusto Vicente
 */
@Documented
@Constraint(validatedBy = DataRequiredValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataRequired {

    String message() default "This field is required";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
