package com.discount.java11.Repository;

import com.discount.java11.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PersonRepository extends JpaRepository<Person, Long> {

}
