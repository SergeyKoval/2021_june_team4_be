package com.exadel.discount.exception;

/**
 * This class is intended for being an exception for database data deletion.
 *
 * @see com.exadel.discount.exception.APIException
 */

public class DeletionRestrictedException extends APIException {

    /**
     * Construction the class with an exception message.
     *
     * @param message a given exception message from the code.
     */
    public DeletionRestrictedException(String message) {
        super(message);
    }
}
