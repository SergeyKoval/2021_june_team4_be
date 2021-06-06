package com.discount.java11.Dto;

import com.discount.java11.Entity.Order;
import com.discount.java11.Entity.Person;
import lombok.Data;

import javax.persistence.ManyToOne;
@Data
public class OrderDto {
    private Long id;
    private int price;

    public static OrderDto from(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setPrice(order.getPrice());
    }
}
