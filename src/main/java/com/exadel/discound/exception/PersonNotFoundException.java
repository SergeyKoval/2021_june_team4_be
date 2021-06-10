package com.exadel.discound.exception;

import java.text.MessageFormat;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(Long id) {
        MessageFormat.format("Could not found person with id:{0}", id);
    }
}
