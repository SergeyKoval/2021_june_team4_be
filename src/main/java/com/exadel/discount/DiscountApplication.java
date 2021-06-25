package com.exadel.discount;

import com.exadel.discount.entity.*;
import com.exadel.discount.repository.CityRepository;
import com.exadel.discount.repository.CountryRepository;
import com.exadel.discount.repository.DiscountRepository;
import com.exadel.discount.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class DiscountApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscountApplication.class, args);
    }


    @Bean
    public CommandLineRunner run(UserRepository userRepository, CountryRepository countryRepository, CityRepository cityRepository, DiscountRepository discountRepository){
        return (String[] args) -> {
            // Users 2 creation and discount in DB
            User user = new User();
            User admin = new User();
            City kiev = new City();
            List<City> cities = new ArrayList<>();
            Country ukraina = new Country();
            Discount discount = new Discount();

            kiev.setCountry(ukraina);
            kiev.setName("Kiev");
            cities.add(kiev);
            ukraina.setName("Ukraine");
            ukraina.setCities(cities);
            user.setFirstName("Jon");
            user.setLastName("Herring");
            user.setPhone("380675553311");
            user.setEmail("Herring@gmail.com");
            user.setLogin("user");
            user.setPassword("password");
            user.setRole(Role.USER);
            user.setCity(kiev);
            admin.setFirstName("Andrey");
            admin.setLastName("Adam");
            admin.setPhone("380673337779");
            admin.setEmail("Adam@gmail.com");
            admin.setLogin("admin");
            admin.setPassword("adminpassword");
            admin.setRole(Role.ADMIN);
            admin.setCity(kiev);

            discount.setName("SuperDiscount");
            discount.setPromo("AlmostFree");

            discountRepository.save(discount);
            countryRepository.save(ukraina);
            cityRepository.save(kiev);
            userRepository.save(user);
            userRepository.save(admin);
        };
    }
}
