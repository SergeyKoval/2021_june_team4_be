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
            City kiev = new City();
            List<City> cities = new ArrayList<>();
            Country ukraina = new Country();
            User user1 = new User();
            Discount discount = new Discount();

            kiev.setCountry(ukraina);
            kiev.setName("Kiev");
            cities.add(kiev);
            ukraina.setName("Ukraine");
            ukraina.setCities(cities);
            //"Jon", "Herring", "380675553311", "Herring@gmail.com", "user", "password", Role.USER, kiev);
            user1.setFirstName("Jon");
            user1.setLastName("Herring");
            user1.setPhone("380675553311");
            user1.setEmail("Herring@gmail.com");
            user1.setLogin("user");
            user1.setPassword("password");
            user1.setRole(Role.USER);
            user1.setCity(kiev);

            discount.setName("SuperDiscount");
            discount.setPromo("AlmostFree");

            discountRepository.save(discount);
            countryRepository.save(ukraina);
            cityRepository.save(kiev);
            userRepository.save(user1);
        };
    }
}
