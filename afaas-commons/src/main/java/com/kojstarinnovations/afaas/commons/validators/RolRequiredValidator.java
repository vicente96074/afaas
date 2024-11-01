package com.kojstarinnovations.afaas.commons.validators;

import com.kojstarinnovations.afaas.commons.validation.DataRequired;
import com.kojstarinnovations.afaas.commons.validation.RolRequired;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RolRequiredValidator implements ConstraintValidator<RolRequired, Set<String>> {

    @Override
    public boolean isValid(Set<String> value, ConstraintValidatorContext context) {

        if (value == null || value.isEmpty()) {
            return false;
        }

        for (@DataRequired(message = "Rol is required") String rol : value) {
            if (rol == null || rol.isEmpty()) {
                return false;
            }
        }

        return true;
    }
}
