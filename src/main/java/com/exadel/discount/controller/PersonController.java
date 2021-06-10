package com.exadel.discount.controller;

import com.exadel.discount.dto.PersonDto;
import com.exadel.discount.entity.Person;
import com.exadel.discount.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
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
    public ResponseEntity<PersonDto> getPerson(@PathVariable final UUID id) {
        Person person = personService.findPersonById(id);
        return new ResponseEntity<>(PersonDto.from(person), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PersonDto> deletePerson(@PathVariable final UUID id) {
        Person person = personService.deletePerson(id);
        return new ResponseEntity<>(PersonDto.from(person), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<PersonDto> editPerson(@PathVariable final UUID id,
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

    @PostMapping("{personId}/order/{couponId}/add")
    public ResponseEntity<PersonDto> addCouponToPerson(@PathVariable final UUID personId,
                                                       @PathVariable final UUID couponId) {
        Person person = personService.addCouponToPerson(personId, couponId);
        return new ResponseEntity<>(PersonDto.from(person), HttpStatus.OK);
    }

    @DeleteMapping("{personId}/coupon/{couponId}/remove")
    public ResponseEntity<PersonDto> removeCouponFromPerson(@PathVariable final UUID personId,
                                                            @PathVariable final UUID couponId) {
        Person person = personService.removeCouponFromPerson(personId, couponId);
        return new ResponseEntity<>(PersonDto.from(person), HttpStatus.OK);
    }
}
