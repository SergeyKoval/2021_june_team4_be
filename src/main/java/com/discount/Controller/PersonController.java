package com.discount.Controller;

import com.discount.Dto.PersonDto;
import com.discount.Entity.Person;
import com.discount.Service.PersonService;
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
                                                @RequestBody final PersonDto personDto) {
        Person person = personService.editPerson(id, Person.from(personDto));
        return new ResponseEntity<>(PersonDto.from(person), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<PersonDto>> getPeopleByName(@PathVariable String name) {
        List<Person> people = personService.findPersonByName(name);
        List<PersonDto> peopleDto = people.stream().map(PersonDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(peopleDto, HttpStatus.OK);
    }

    @PostMapping("{personId}/order/{orderId}/add")
    public ResponseEntity<PersonDto> addOrderToPerson(@PathVariable final Long personId,
                                                      @PathVariable final Long orderId) {
        Person person = personService.addOrderToPerson(personId, orderId);
        return new ResponseEntity<>(PersonDto.from(person), HttpStatus.OK);
    }

    @DeleteMapping("{personId}/order/{orderId}/remove")
    public ResponseEntity<PersonDto> removeOrderFromPerson(@PathVariable final Long personId,
                                                           @PathVariable final Long orderId) {
        Person person = personService.removeOrderFromPerson(personId, orderId);
        return new ResponseEntity<>(PersonDto.from(person), HttpStatus.OK);
    }
}
