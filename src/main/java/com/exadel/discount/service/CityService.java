package com.exadel.discount.service;

import com.exadel.discount.dto.CityDTO;

import java.util.List;
import java.util.UUID;

public interface CityService {
    List<CityDTO> findAll();

    List<CityDTO> findAllByCountry(String countryName);

    CityDTO findById(UUID id);

    CityDTO findByName(String name);

    CityDTO save(CityDTO cityDTO);

    void deleteById(UUID id);
}
