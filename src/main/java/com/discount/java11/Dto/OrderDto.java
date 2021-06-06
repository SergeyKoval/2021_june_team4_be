package com.discount.java11.Dto;

import com.discount.java11.Entity.Order;
import com.discount.java11.Entity.Person;
import lombok.Data;

import javax.persistence.ManyToOne;
@Data
public class OrderDto {
    private Long id;
    private int price;
    private String SerialNumber;

    public static OrderDto from(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setPrice(order.getPrice());
        orderDto.setSerialNumber(order.getSerialNumber());
        return orderDto;
    }
}
