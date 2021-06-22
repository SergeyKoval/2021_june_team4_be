package com.exadel.discount.service;

import com.exadel.discount.dto.CityDTO;
import com.exadel.discount.dto.CountryDTO;

import java.util.List;
import java.util.UUID;

public interface CountryService {
    List<CountryDTO> findAll();

    CountryDTO findOneById(UUID id);

    CountryDTO findByName(String name);

    CountryDTO save(CountryDTO countryDTO);

    void deleteById(UUID id);
}
