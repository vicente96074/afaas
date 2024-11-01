package com.kojstarinnovations.afaas.commons.validators;

import com.kojstarinnovations.afaas.commons.validation.LocalDateRequired;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LocalDateRequiredValidator implements ConstraintValidator<LocalDateRequired, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        try {
            LocalDate date = (LocalDate) value;
            return date != null;
        } catch (Exception e) {
            return false;
        }
    }
}
