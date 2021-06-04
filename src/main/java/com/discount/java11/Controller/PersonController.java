package com.discount.java11.Controller;

import com.discount.java11.Entity.Person;
import com.discount.java11.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {


    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/all")
    public List<Person> getAllPeople() {

        return personService.findAllPeople();
    }

    @GetMapping("/id/{id}")
    public Person findPeopleById(@PathVariable Long id) {
        return personService.findPersonById(id);

    }

    @GetMapping("/firstname/{firstName}")
    public List<Person> getPeopleByFirstName(@PathVariable String firstName) {
        return personService.findPersonByName(firstName);
    }

    @GetMapping("/secondname/{secondName}")
    public List<Person> getPeopleBySecondName(@PathVariable String secondName) {
        return personService.findPersonByName(secondName);
    }
}
