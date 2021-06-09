package com.discount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public CommandLineRunner loadData(PersonRepository personRepository, OrderRepository orderRepository) {
//        return (args) -> {
//            // save a couple of customers
//            Person person1 = new Person("Jack", "Bauer", "380675553311", "Herring@gmail.com", "Jack", "password", "user");
//            Person person2 = new Person("Chloe", "O'Brian", "380895544331", "Malcolm@gmail.com",  "Chloe", "password", "user");
//            Person person3 = new Person("Kim", "Jackson", "380683337799", "Jackson@gmail.com", "Jackson", "admin", "admin");
//            personRepository.save(person1);
//            personRepository.save(person2);
//            personRepository.save(person3);
//            orderRepository.save(new Order(123, "12343er", person1));
//            orderRepository.save(new Order(22, "9999er", person1));
//            orderRepository.save(new Order(123, "12343er",person2));
//            orderRepository.save(new Order(123, "12343er",person3));
//
//        };
//    }
}

