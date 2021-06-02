package com.discount.java11.Repository;

import com.discount.java11.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByLastNameStartsWithIgnoreCase(String lastName);

    List<Person> findByFirstNameStartsWithIgnoreCase(String lastName);

    List<Person> findByYearOfBirth(int yearOfBirth);

    List<Person> findByTheFirstTime(boolean theFirstTime);

}
