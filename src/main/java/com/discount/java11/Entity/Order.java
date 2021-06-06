package com.discount.java11.Entity;

import com.discount.java11.Dto.OrderDto;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int price;
    private String SerialNumber;

    @ManyToOne
    private Person person;

    public Order() {
    }

    public static Order from(OrderDto orderDto) {
        Order order = new Order();
        order.setPrice(orderDto.getPrice());
        order.setSerialNumber(orderDto.getSerialNumber());
        return order;
    }
}
