package com.discount.Exception;

import java.text.MessageFormat;

public class OrderNotFoundAtPriceException extends RuntimeException{
    public OrderNotFoundAtPriceException(int price){
        super(MessageFormat.format("Could not find order at price:{0} ",price));
    }
}