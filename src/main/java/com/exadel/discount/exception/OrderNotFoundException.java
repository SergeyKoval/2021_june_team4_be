package com.exadel.discount.exception;

import java.text.MessageFormat;
import java.util.UUID;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(UUID id){
        super(MessageFormat.format("Could not find order with id:{0}",id));
    }
}
