package com.kojstarinnovations.afaas.commons.validators;

import com.kojstarinnovations.afaas.commons.validation.IntegerRequired;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class IntegerRequiredValidator implements ConstraintValidator<IntegerRequired, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try{
            Integer.parseInt(value.toString());
            return Integer.parseInt(value.toString()) > 0;
        }catch (Exception e){
            return false;
        }
    }
}
