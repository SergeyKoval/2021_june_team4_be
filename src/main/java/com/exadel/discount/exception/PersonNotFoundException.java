package com.exadel.discount.exception;

import java.text.MessageFormat;
import java.util.UUID;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(UUID id) {
        MessageFormat.format("Could not found person with id:{0}", id);
    }
}
