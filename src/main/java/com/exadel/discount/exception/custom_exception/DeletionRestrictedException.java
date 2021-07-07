package com.exadel.discount.exception.custom_exception;

public class DeletionRestrictedException extends RuntimeException {

    public DeletionRestrictedException(String message) {
        super(message);
    }
}
