package com.discount.java11.Entity;

import com.discount.java11.Dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "order")

public class Order {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private int price;
    private String SerialNumber;

    @ManyToOne
    @JoinColumn
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
