package com.discount.java11.Service;

import com.discount.java11.Entity.Person;

import java.util.List;

public interface PersonService {
    Person findPersonById(Long id);

    List<Person> findAllPeople();

    List<Person> findPersonByFirstName(String firstName);

    List<Person> findPersonBySecondName(String secondName);
}


