package com.discount.java11.Entity;

import com.discount.java11.Dto.PersonDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persons")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class Person {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "personsIdSeq", sequenceName = "persons_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personsIdSeq")
    private Long id;
    private String firstName;
    private String secondName;
    private String telephone;
    @NotNull
    private String email;
    @NotNull
    private String login;
    @JsonIgnore
    @NotNull
    private String password;
    @NotNull
    private String Role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_person")
    private List<Order> orders = new ArrayList<>();

    public Person() {
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public static Person from(PersonDto personDto) {
        Person person = new Person();
        person.setFirstName(personDto.getFirstName());
        person.setSecondName(personDto.getSecondName());
        person.setTelephone(personDto.getTelephone());
        person.setEmail(personDto.getEmail());
        person.setLogin(personDto.getLogin());
        person.setPassword(personDto.getPassword());
        person.setRole(personDto.getRole());
        return person;
    }
}
