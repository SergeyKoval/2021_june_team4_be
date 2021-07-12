package com.exadel.discount.exception;

/**
 * This class is intended for being an exception for situations where
 * a search key finds nothing in a database.
 *
 * @see com.exadel.discount.exception.APIException
 */

public class NotFoundException extends APIException {

    /**
     * Construction the class with an exception message.
     *
     * @param message a given exception message from the code.
     */
    public NotFoundException(String message) {
        super(message);
    }
}
