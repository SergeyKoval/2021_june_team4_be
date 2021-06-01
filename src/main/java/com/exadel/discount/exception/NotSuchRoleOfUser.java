package com.exadel.discount.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotSuchRoleOfUser extends RuntimeException {
    public NotSuchRoleOfUser(String role) {
        super(MessageFormat.format("Does not exist such role :{0}", role));
    }
}
