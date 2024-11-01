package com.kojstarinnovations.afaas.commons.validators;

import com.kojstarinnovations.afaas.commons.validation.DecimalRequired;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DecimalRequiredValidator implements ConstraintValidator<DecimalRequired, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        //Try to parse the value to a BigDecimal, if it fails, it means that the value is not a number
        try {
            new BigDecimal(value.toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
