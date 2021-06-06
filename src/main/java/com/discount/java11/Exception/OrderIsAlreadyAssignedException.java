package com.discount.java11.Exception;

import java.text.MessageFormat;

public class OrderIsAlreadyAssignedException extends RuntimeException {
    public OrderIsAlreadyAssignedException(final Long orderId, final Long personId) {
        super(MessageFormat.format("Order: {0} could not find order at price:{1} ", orderId, personId));
    }
}
