package com.kojstarinnovations.afaas.commons.validators;

import com.kojstarinnovations.afaas.commons.validation.DataRequired;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

/**
 * Class definition for the DataRequiredValidator
 * This class is used to validate if a field is required
 *
 * @author Augusto Vicente
 */
@Component
public class DataRequiredValidator implements ConstraintValidator<DataRequired, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !value.isEmpty();
    }
}
