package com.exadel.discount.exception;

public class DeletionRestrictedException extends RuntimeException {

    public DeletionRestrictedException(String message) {
        super(message);
    }
}
