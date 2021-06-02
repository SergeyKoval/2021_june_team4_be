package com.discount.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int price;

    @ManyToOne(fetch = FetchType.EAGER)
    private Person person;
}
