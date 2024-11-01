package com.kojstarinnovations.afaas.commons.exception;

/**
 * DuplicateException
 *
 * @author Augusto Vicente
 */
public class DuplicateException extends RuntimeException {

    /**
     * Constructor with message
     *
     * @param message the message
     */
    public DuplicateException(String message) {
        super(message);
    }
}
