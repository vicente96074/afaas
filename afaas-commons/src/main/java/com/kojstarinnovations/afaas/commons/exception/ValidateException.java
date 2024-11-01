package com.kojstarinnovations.afaas.commons.exception;

/**
 * ValidateException
 *
 * @author Augusto Vicente
 */
public class ValidateException extends RuntimeException{

    /**
     * Constructor with message
     *
     * @param message the message
     */
    public ValidateException(String message){
        super(message);
    }
}
