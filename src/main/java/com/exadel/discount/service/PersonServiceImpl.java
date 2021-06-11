package com.exadel.discount.service;

import com.exadel.discount.entity.Coupon;
import com.exadel.discount.entity.Person;
import com.exadel.discount.exception.CouponIsAlreadyAssignedException;
import com.exadel.discount.exception.CouponNotFoundAtSerialNumberException;
import com.exadel.discount.exception.PersonNotFoundException;
import com.exadel.discount.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final CouponService couponService;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, CouponService couponService) {
        this.personRepository = personRepository;
        this.couponService = couponService;
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    public Person deletePerson(UUID id) {
        Person personDeleted = null;
        try {
            personDeleted = findPersonById(id);
        } catch (PersonNotFoundException e) {
            e.printStackTrace();
        }
        if (personDeleted != null)
            personRepository.delete(personDeleted);
        return personDeleted;
    }

    @Override
    public Person findPersonById(UUID id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Override
    public List<Person> findAllPeople() {
        return StreamSupport.
                stream(personRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

   // List<Person> findPersonByName(@Param("searchTerm") String searchTerm);
    @Override
    public List<Person> findPersonByName(String name) {
        return new ArrayList<>(personRepository.findPersonByName(name));
    }

    @Transactional
    public Person editPerson(UUID id, Person person) {
        Person editedPerson = findPersonById(id);
        editedPerson.setFirstName(person.getFirstName());
        editedPerson.setSecondName(person.getSecondName());
        editedPerson.setEmail(person.getEmail());
        editedPerson.setPhone(person.getPhone());
        editedPerson.setRole(person.getRole());
        editedPerson.setLogin(person.getLogin());
        editedPerson.setPassword(person.getPassword());
        return editedPerson;
    }

    @Transactional
    public Person addCouponToPerson(UUID couponId, UUID orderId) {
        Person person = findPersonById(couponId);
        Coupon coupon = couponService.findCouponById(orderId);
        if (Objects.nonNull(coupon.getPerson())) {
            throw new CouponIsAlreadyAssignedException(orderId,
                    coupon.getPerson().getId());
        }
        person.addCoupon(coupon);
        coupon.setPerson(person);
        return person;
    }

    @Transactional
    public Person removeCouponFromPerson(UUID personId, UUID couponId) {
        Person person = null;
        Coupon coupon = null;
        try {
            coupon = couponService.findCouponById(couponId);
        } catch (CouponNotFoundAtSerialNumberException e) {
            e.printStackTrace();
        }
        try {
            person = findPersonById(couponId);
        } catch (PersonNotFoundException e) {
            e.printStackTrace();
        }
        if (person != null) {
            person.removeCoupon(coupon);
        }
        return person;
    }
}

