package com.exadel.discound.dto;

import com.exadel.discound.entity.Order;
import lombok.Data;

import java.util.Objects;

@Data
public class OrderDto {
    private Long id;
    private int price;
    private String SerialNumber;
    private PlainPersonDto plainPersonDto;

    public static OrderDto from(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setPrice(order.getPrice());
        orderDto.setSerialNumber(order.getSerialNumber());
        if (Objects.nonNull(order.getPerson())) {
            orderDto.setPlainPersonDto(PlainPersonDto.from(order.getPerson()));
        }
        return orderDto;
    }
}
