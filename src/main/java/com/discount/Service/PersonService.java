package com.discount.Service;

import com.discount.Entity.Person;

import java.util.List;

public interface PersonService {
    Person findPersonById(Long id);

    List<Person> findAllPeople();

    List<Person> findPersonByFirstName(String firstName);

    List<Person> findPersonBySecondName(String secondName);
}


