package com.exadel.discount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAutoConfiguration(exclude = {WebMvcAutoConfiguration.class })
@ComponentScan
public class DiscountApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscountApplication.class, args);
    }
}
