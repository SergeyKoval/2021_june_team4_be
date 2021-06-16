package com.exadel.discount.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserSuchEmailNotFoundException extends RuntimeException {
    public UserSuchEmailNotFoundException(String email) {
        MessageFormat.format("Could not found person with email:{0} ", email);
    }
}