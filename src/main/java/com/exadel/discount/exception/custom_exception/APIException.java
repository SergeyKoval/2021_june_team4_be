package com.exadel.discount.exception.custom_exception;

public abstract class APIException extends RuntimeException {

    public APIException(String message) {
        super(message);
    }
}
