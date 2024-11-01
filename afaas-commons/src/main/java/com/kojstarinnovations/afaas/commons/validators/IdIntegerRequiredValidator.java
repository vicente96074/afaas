package com.kojstarinnovations.afaas.commons.validators;

import com.kojstarinnovations.afaas.commons.validation.IdIntegerRequired;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

/**
 * IdIntegerRequired annotation definition, this annotation is used to validate if a field is an integer
 *
 * @author Augusto Vicente
 */
@Component
public class IdIntegerRequiredValidator implements ConstraintValidator<IdIntegerRequired, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        //Try to parse the value to an Integer, if it fails, it means that the value is not an integer
        try{
            Integer.parseInt(value.toString());
            return Integer.parseInt(value.toString()) > 0;
        }catch (Exception e){
            return false;
        }
    }
}
