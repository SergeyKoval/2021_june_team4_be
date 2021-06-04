package com.discount.java11.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "persons")
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String secondName;
    private String telephone;
    private String email;
    private String login;
    @JsonIgnore
    private String password;
    private String Role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Set<Order> orders = new HashSet<>();

    public Person() {
    }
    public void addOrder(Order order){
        orders.add(order);
    }
    public void removeOrder(Order order){
        orders.remove(order);
    }
}