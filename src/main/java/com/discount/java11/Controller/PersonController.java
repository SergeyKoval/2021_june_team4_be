package com.discount.java11.Controller;

import com.discount.java11.Dto.PersonDto;
import com.discount.java11.Entity.Person;
import com.discount.java11.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<PersonDto> addPerson(@RequestBody final PersonDto personDto) {
        Person person = personService.addPerson(Person.from(personDto));
        return new ResponseEntity<>(PersonDto.from(person), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> getPeople() {
        List<Person> people = personService.findAllPeople();
        List<PersonDto> peopleDto = people.stream().map(PersonDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(peopleDto, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable final Long id) {
        Person person = personService.findPersonById(id);
        return new ResponseEntity<>(PersonDto.from(person), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PersonDto> deletePerson(@PathVariable final Long id) {
        Person person = personService.deletePerson(id);
        return new ResponseEntity<>(PersonDto.from(person), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<PersonDto> editPerson(@PathVariable final Long id,
                                                @PathVariable final PersonDto personDto) {
        Person person = personService.editPerson(id, Person.from(personDto));
        return new ResponseEntity<>(PersonDto.from(person), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<PersonDto>> getPeopleBySecondName(@PathVariable String name) {
        List<Person> people = personService.findPersonByName(name);
        List<PersonDto> peopleDto = people.stream().map(PersonDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(peopleDto, HttpStatus.OK);
    }

    @PostMapping("{personId}/orders/{orderId}/add")
    public ResponseEntity<PersonDto> addOrderToPerson(@PathVariable final Long personId,
                                                      @PathVariable final Long orderId) {
        Person person = personService.addOrderToPerson(personId, orderId);
        return new ResponseEntity<>(PersonDto.from(person), HttpStatus.OK);
    }

    @DeleteMapping("{personId}/orders/{orderId}/remove")
    public ResponseEntity<PersonDto> removeOrderFromPerson(@PathVariable final Long personId,
                                                           @PathVariable final Long orderId) {
        Person person = personService.removeOrderFromPerson(personId, orderId);
        return new ResponseEntity<>(PersonDto.from(person), HttpStatus.OK);
    }
}
