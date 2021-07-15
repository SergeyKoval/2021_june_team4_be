package com.exadel.discount.service;

import com.exadel.discount.model.dto.CountryDTO;
import com.exadel.discount.service.impl.CountryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CountryServiceTest {


    @Autowired
    private CountryServiceImpl countryService;


    private CountryDTO input;

    @BeforeEach
    public void setUp() {
        input = CountryDTO.builder()
                .name("test")
                .build();
    }


    @Test
    public void testSave() {

        var result = countryService.save(input);

        Assertions.assertNotNull(result);

    }
}