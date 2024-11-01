package com.kojstarinnovations.afaas.commons.exception;

/**
 * Invalid Data Exception
 *
 * @author Augusto Vicente
 */
public class InvalidDataException extends RuntimeException {

    /**
     * Invalid Data Exception
     *
     * @param message the message
     */
    public InvalidDataException(String message) {
        super(message);
    }

}
