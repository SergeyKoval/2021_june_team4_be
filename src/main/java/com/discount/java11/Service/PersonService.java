package com.discount.java11.Service;

import com.discount.java11.Entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PersonService {
    Person findPersonById(Long id);

    List<Person> findAllPeople();

    List<Person> findPersonByFirstName(String firstName);

    List<Person> findPersonBySecondName(String secondName);
}


