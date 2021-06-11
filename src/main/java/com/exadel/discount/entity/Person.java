package com.exadel.discount.entity;

import com.exadel.discount.dto.PersonDto;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "persons")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @SequenceGenerator(name = "personsIdSeq", sequenceName = "persons_id_seq", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personsIdSeq")
    private UUID id;
    private String firstName;
    private String secondName;
    private String phone;
    private String email;
    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    private Person.Role role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private List<Coupon> coupons = new ArrayList<>();

    public enum Role{USER,ADMIN
    }

    public Person() {
    }

    public void addCoupon(Coupon coupon) {
        coupons.add(coupon);
    }

    public void removeCoupon(Coupon coupon) {
        coupons.remove(coupon);
    }

    public static Person from(PersonDto personDto) {
        Person person = new Person();
        person.setFirstName(personDto.getFirstName());
        person.setSecondName(personDto.getSecondName());
        person.setPhone(personDto.getPhone());
        person.setEmail(personDto.getEmail());
        person.setLogin(personDto.getLogin());
        person.setPassword(personDto.getPassword());
        person.setRole(personDto.getRole());
        return person;
    }

    public Person(String firstName, String secondName, String phone, String email, String login, String password, com.exadel.discount.entity.Person.Role role) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
