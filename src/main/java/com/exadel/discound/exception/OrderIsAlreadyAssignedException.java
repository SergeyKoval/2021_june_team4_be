package com.exadel.discound.exception;

import java.text.MessageFormat;

public class OrderIsAlreadyAssignedException extends RuntimeException {
    public OrderIsAlreadyAssignedException(final Long orderId, final Long personId) {
        super(MessageFormat.format("Order: {0} could not find order at price:{1} ", orderId, personId));
    }
}
