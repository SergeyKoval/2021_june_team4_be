package com.discount.java11.Service;


import com.discount.java11.Entity.Person;
import com.discount.java11.Repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person findPersonById(Long id) {
        Person person;
        person = personRepository.findById(id).get();
        return person;
    }

    @Override
    public List<Person> findAllPeople() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> findPersonByFirstName(String firstName) {
        List<Person> peopleList = personRepository.findAll();
        List<Person> peopleWithTheSameFirstName = new ArrayList();
        for (Person person : peopleList) {
            if (person.getFirstName().equals(firstName))
                peopleWithTheSameFirstName.add(person);
        }
        return peopleWithTheSameFirstName;
    }

    @Override
    public List<Person> findPersonBySecondName(String secondName) {
        List<Person> peopleList = personRepository.findAll();
        List<Person> peopleWithTheSameSecondName = new ArrayList();
        for (Person person : peopleList) {
            if (person.getSecondName().equals(secondName))
                peopleWithTheSameSecondName.add(person);
        }
        return peopleWithTheSameSecondName;
    }
}
