package com.discount.java11.Service;


import com.discount.java11.Entity.Order;
import com.discount.java11.Entity.Person;
import com.discount.java11.Exception.OrderIsAlreadyAssignedException;
import com.discount.java11.Exception.PersonNotFoundException;
import com.discount.java11.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final OrderServiceImpl orderService;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, OrderServiceImpl orderService) {
        this.personRepository = personRepository;
        this.orderService = orderService;
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    public Person deletePerson(Long id) {
        try {
            Person personDeleted = findPersonById(id);
            personRepository.delete(personDeleted);
        } catch (PersonNotFoundException e) {
            e.printStackTrace();
        }
        return findPersonById(id);
    }

    @Override
    public Person findPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Override
    public List<Person> findAllPeople() {
        return StreamSupport.
                stream(personRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findPersonByName(String name) {
        return StreamSupport.stream(personRepository.findPersonByName(name).spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    public Person editPerson(Long id, Person person) {
        Person editedPerson = findPersonById(id);
        editedPerson.setFirstName(person.getFirstName());
        editedPerson.setSecondName(person.getSecondName());
        editedPerson.setEmail(person.getEmail());
        editedPerson.setTelephone(person.getTelephone());
        editedPerson.setRole(person.getRole());
        editedPerson.setLogin(person.getLogin());
        editedPerson.setPassword(person.getPassword());
        return editedPerson;
    }

    @Transactional
    public Person addOrderToPerson(Long orderId, Long personId) {
        Person person = findPersonById(personId);
        Order order = orderService.findOrderById(orderId);
        if(Objects.nonNull(order.getPerson())){
            throw new OrderIsAlreadyAssignedException(orderId,
                    order.getPerson().getId());
        }
        person.addOrder(order);
        order.setPerson(person);
        return person;
    }

    @Transactional
    public Person removeOrderFromPerson(Long orderId, Long personId) {
        Person person = findPersonById(personId);
        Order order = orderService.findOrderById(orderId);
        person.removeOrder(order);
        return person;
    }

}

