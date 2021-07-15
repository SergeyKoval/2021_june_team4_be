package com.exadel.discount.service;

import com.exadel.discount.model.dto.CountryDTO;
import com.exadel.discount.model.dto.mapper.CountryMapper;
import com.exadel.discount.model.entity.Country;
import com.exadel.discount.repository.CountryRepository;
import com.exadel.discount.service.impl.CountryServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;


import static org.mockito.Mockito.*;

@SpringBootTest
public class CountryServiceTest {
    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CountryMapper countryMapper;

    @InjectMocks
    private CountryServiceImpl countryService;

    private Country expected;

    private CountryDTO countryDTO;

    @BeforeEach
    public void setUp() {
        UUID uuid = UUID.fromString("30469262-f904-4acd-97b0-0cfd89fc25b9");
        expected = new Country();
        expected.setId(uuid);
        expected.setName("Lapland");
        countryDTO = new CountryDTO();
        countryDTO.setId(uuid);
        countryDTO.setName("Lapland");
    }

    @AfterEach
    public void tearDown() {
        expected = null;
        countryDTO = null;
    }

    @Test
    public void testSave() {
        when(countryRepository.save(expected)).thenReturn(expected);
        countryDTO = countryService.save(countryMapper.countryToCountryDTO(expected));

        Assertions.assertEquals(countryMapper.countryToCountryDTO(expected), countryDTO);
        verify(countryRepository, times(1)).save(expected);
    }
}