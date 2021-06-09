package com.discount.Exception;

import java.text.MessageFormat;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Long id){
        super(MessageFormat.format("Could not find order with id:{0}",id));
    }
}
