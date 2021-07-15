package com.exadel.discount.service;

import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.model.dto.CountryDTO;
import com.exadel.discount.model.dto.mapper.CountryMapper;
import com.exadel.discount.model.entity.Country;
import com.exadel.discount.repository.CountryRepository;
import com.exadel.discount.service.impl.CountryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CountryServiceTest {

    private static final UUID ID = UUID.fromString("971bf698-f3ea-4a97-85e8-0a2a770736d6");

    @Autowired
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
    public void testFindALl() {
        List<Country> expected = List.of(new Country(), new Country());

        when(countryRepository.findAll()).thenReturn(expected);
        CountryServiceImpl implS = Mockito.mock(CountryServiceImpl.class);
        List<CountryDTO> actual = implS.findAll();

        Assertions.assertEquals(actual, mapper.getListDTO(expected));
    }


    public void testFindById() {
        Country country = mapper.countryDTOToCountry(input);
        when(countryRepository.findById(UUID.randomUUID())).thenReturn(Optional.of(country));
        CountryDTO actual = countryService.findById(UUID.randomUUID());

        Assertions.assertEquals(input,actual);
    }

    @Test
    public void testExceptionFindById(){
        Exception exception = assertThrows(NotFoundException.class,
                ()-> countryService.findById(ID));
        Assertions.assertEquals("Country with id "+ID+" not found",exception.getMessage());

    }

    @Test
    public void testDeleteById(){
        when(countryRepository.findById(ID)).thenReturn(Optional.of(new Country()));
        doNothing().when(countryRepository).delete(any(Country.class));
        countryRepository.deleteById(UUID.randomUUID());
    }

    @Test
    public void testExceptionDeleteById(){
        Exception exception = assertThrows(NotFoundException.class,
                () -> countryService.deleteById(ID));
        Assertions.assertEquals("Country with Id "+ID+" not found",exception.getMessage());
    }

    @Test
    public void testFindByName(){

    }

    @Test
    public void testExceptionFindByName(){
        String  name = "f";
        Exception exception = assertThrows(NotFoundException.class,
                () -> countryService.findByName(name));
        Assertions.assertEquals("Country with name "+name+" not found",exception.getMessage());
    }
}