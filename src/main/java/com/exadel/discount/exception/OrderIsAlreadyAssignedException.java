package com.exadel.discount.exception;

import java.text.MessageFormat;
import java.util.UUID;

public class OrderIsAlreadyAssignedException extends RuntimeException {
    public OrderIsAlreadyAssignedException(final UUID orderId, final UUID personId) {
        super(MessageFormat.format("Order: {0} could not find order at price:{1} ", orderId, personId));
    }
}
