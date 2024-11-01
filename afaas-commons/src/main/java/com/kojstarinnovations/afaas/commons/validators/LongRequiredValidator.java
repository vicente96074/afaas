package com.kojstarinnovations.afaas.commons.validators;

import com.kojstarinnovations.afaas.commons.validation.LongRequired;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class LongRequiredValidator implements ConstraintValidator<LongRequired, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try{
            Long.parseLong(value.toString());
            return Long.parseLong(value.toString()) > 0;
        }catch (Exception e){
            return false;
        }
    }
}
