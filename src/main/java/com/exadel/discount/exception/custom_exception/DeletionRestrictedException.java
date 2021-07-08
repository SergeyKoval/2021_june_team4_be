package com.exadel.discount.exception.custom_exception;

public class DeletionRestrictedException extends APIException {

    public DeletionRestrictedException(String message) {
        super(message);
    }
}
