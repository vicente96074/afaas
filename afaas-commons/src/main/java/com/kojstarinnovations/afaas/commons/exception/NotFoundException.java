package com.kojstarinnovations.afaas.commons.exception;

/**
 * Not Found Exception customized
 *
 * @author Augusto Vicente
 */
public class NotFoundException extends RuntimeException {

    /**
     * Constructor with message
     *
     * @param message the message
     */
    public NotFoundException(String message) {
        super(message);
    }

}
