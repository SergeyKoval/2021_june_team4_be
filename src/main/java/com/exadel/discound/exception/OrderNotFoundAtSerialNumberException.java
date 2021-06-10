package com.exadel.discound.exception;

import java.text.MessageFormat;

public class OrderNotFoundAtSerialNumberException extends RuntimeException {

    public OrderNotFoundAtSerialNumberException(String serialNumber) {
        super(MessageFormat.format("Could not find order at serialNumber:{0} ", serialNumber));
    }
}

