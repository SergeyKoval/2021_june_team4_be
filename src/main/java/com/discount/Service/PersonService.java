package com.discount.Service;

import com.discount.Entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {
    Person findPersonById(Long id);

    Person addPerson(Person person);

    Person deletePerson(Long id);

    List<Person> findAllPeople();

    List<Person> findPersonByName(String name);

    Person editPerson(Long id, Person person);

    Person addOrderToPerson(Long orderId, Long personId);

    Person removeOrderFromPerson(Long orderId, Long personId);
}


