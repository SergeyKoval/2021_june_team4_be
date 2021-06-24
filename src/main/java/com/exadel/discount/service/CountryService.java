package com.exadel.discount.service;

import com.exadel.discount.dto.CityDTO;
import com.exadel.discount.dto.CountryDTO;

import java.util.List;
import java.util.UUID;

public interface CountryService {
    List<CountryDTO> findAllCountries();

    CountryDTO findCountryById(UUID id);

    CountryDTO findCountryByName(String name);

    CountryDTO save(CountryDTO countryDTO);

    void deleteById(UUID id);
}
