package com.exadel.discount.exception;

import java.text.MessageFormat;
import java.util.UUID;

public class CuoponNotFoundException extends RuntimeException{
    public CuoponNotFoundException(UUID id){
        super(MessageFormat.format("Could not find Coupon with id:{0}",id));
    }
}
