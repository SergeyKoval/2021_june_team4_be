package com.discount.java11;

import com.discount.java11.Entity.Order;
import com.discount.java11.Entity.Person;
import com.discount.java11.Repository.OrderRepository;
import com.discount.java11.Repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner loadData(PersonRepository personRepository) {
        return (args) -> {
            // save a couple of customers
            personRepository.save(new Person("Jack", "Bauer", "380675553311", "Herring@gmail.com", "Jack", "password", "user"));
            personRepository.save(new Person("Chloe", "O'Brian", "380895544331", "Malcolm@gmail.com",  "Chloe", "password", "user"));
            personRepository.save(new Person("Kim", "Jackson", "380683337799", "Jackson@gmail.com", "Jackson", "admin", "admin"));
        };
    }
}

