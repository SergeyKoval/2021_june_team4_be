package com.exadel.discount.exception;

/**
 * This abstract class is intended for being
 * our main logical class in {@link com.exadel.discount.controller.GlobalExceptionHandler}.
 */

public abstract class APIException extends RuntimeException {

    /**
     * Construction the class with an exception message.
     *
     * @param message a given exception message from the inheritors of this class.
     */
    public APIException(String message) {
        super(message);
    }
}
