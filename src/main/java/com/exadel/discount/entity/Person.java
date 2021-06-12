package com.exadel.discount.entity;

import com.exadel.discount.config.StringToEnumConverter;
import com.exadel.discount.dto.PersonDto;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "persons")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@ToString
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @SequenceGenerator(name = "personsIdSeq", sequenceName = "persons_id_seq", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personsIdSeq")
    private UUID id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    @NotNull
    private String login;
    @NotNull
    private String password;

   // @Enumerated(EnumType.STRING)
   @Convert(converter = StringToEnumConverter.class)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private List<Coupon> coupons = new ArrayList<>();

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
        person.setLastName(personDto.getLastName());
        person.setPhone(personDto.getPhone());
        person.setEmail(personDto.getEmail());
        person.setLogin(personDto.getLogin());
        person.setPassword(personDto.getPassword());
        person.setRole(personDto.getRole());
        return person;
    }

    public Person(String firstName, String lastName, String phone, String email, String login, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
