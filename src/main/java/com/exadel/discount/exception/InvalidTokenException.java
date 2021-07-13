package com.exadel.discount.exception;

/**
 * This class is intended for being an exception which appears during the creation of an access token
 * when a user does not have any role in a database.
 *
 * @see com.exadel.discount.exception.APIException
 */

public class InvalidTokenException extends APIException {

    /**
     * Construction the class with an exception message.
     *
     * @param message a given exception message from the code.
     */
    public InvalidTokenException(String message) {
        super(message);
    }
}
