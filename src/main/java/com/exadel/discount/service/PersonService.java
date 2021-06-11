package com.exadel.discount.service;

import com.exadel.discount.entity.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


public interface PersonService {
    Person findPersonById(UUID id);

    Person addPerson(Person person);

    Person deletePerson(UUID id);

    List<Person> findAllPeople();

    List<Person> findPersonByName(String name);

    Person editPerson(UUID id, Person person);

    Person addCouponToPerson(UUID couponId, UUID personId);

    Person removeCouponFromPerson(UUID couponId, UUID personId);
}


