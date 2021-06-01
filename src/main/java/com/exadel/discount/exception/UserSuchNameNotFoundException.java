package com.exadel.discount.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserSuchNameNotFoundException extends RuntimeException{
    public UserSuchNameNotFoundException(String lastname, String firstname) {
        MessageFormat.format("Could not found person with name:{0} {1}", lastname, firstname);
    }
}