package com.discount.java11.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int price;

    @ManyToOne
    private Person person;

    public Order() {
    }
}
