package com.exadel.discount.service;

import com.exadel.discount.dto.CityDTO;

import java.util.List;
import java.util.UUID;

public interface CityService {
    List<CityDTO> findAllCities();

    List<CityDTO> findAllCitiesByCountryId(UUID id);

    CityDTO findCityById(UUID id);

    CityDTO findCityByName(String name);

    CityDTO save(CityDTO cityDTO);

    void deleteById(UUID id);
}
