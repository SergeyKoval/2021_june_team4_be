package com.discount.java11.Entity;

import com.discount.java11.Dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "orders")

public class Order {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private int price;
    private String SerialNumber;

    @ManyToOne
    private Person person;

    public Order() {
    }

    public Order(int price, String serialNumber, Person person) {
        this.price = price;
        this.SerialNumber = serialNumber;
        this.person = person;
    }

    public static Order from(OrderDto orderDto) {
        Order order = new Order();
        order.setPrice(orderDto.getPrice());
        order.setSerialNumber(orderDto.getSerialNumber());
        return order;


    }
}
