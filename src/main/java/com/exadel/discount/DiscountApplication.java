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

}
