package com.exadel.discount.service;

import com.exadel.discount.model.dto.CountryDTO;
import com.exadel.discount.model.dto.mapper.CountryMapper;
import com.exadel.discount.model.entity.Country;
import com.exadel.discount.repository.CountryRepository;
import com.exadel.discount.service.impl.CountryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@SpringBootTest
public class CountryServiceTest {

    private static final UUID ID = UUID.fromString("971bf698-f3ea-4a97-85e8-0a2a770736d6");

    @Mock
    private CountryServiceImpl countryService;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CountryMapper mapper;

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

    @Test
    public void testFindALl(){
        List<Country> expected = List.of(new Country(), new Country());

        when(countryRepository.findAll()).thenReturn(expected);
        List<CountryDTO> actual = countryService.findAll();

        Assertions.assertEquals(actual,mapper.getListDTO(expected));
    }
}