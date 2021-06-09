package com.discount.Entity;

import com.discount.Dto.OrderDto;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "orders")

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @SequenceGenerator(name = "ordersIdSeq", sequenceName = "orders_id_seq", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordersIdSeq")
    private Long id;
    private int price;
    private String serialNumber;

    @ManyToOne
    private Person person;

    public Order() {
    }

    public Order(int price, String serialNumber, Person person) {
        this.price = price;
        this.serialNumber = serialNumber;
        this.person = person;
    }

    public static Order from(OrderDto orderDto) {
        Order order = new Order();
        order.setPrice(orderDto.getPrice());
        order.setSerialNumber(orderDto.getSerialNumber());
        return order;


    }
}
