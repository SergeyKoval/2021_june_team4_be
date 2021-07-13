package com.exadel.discount.service;

import com.exadel.discount.model.dto.city.BaseCityDTO;
import com.exadel.discount.model.dto.city.CityDTO;

import java.util.List;
import java.util.UUID;

public interface CityService {

    List<CityDTO> findAll();

    List<CityDTO> findAllByCountryName(String countryName);

    List<CityDTO> findAllByCountryId(UUID countryId);

    CityDTO findById(UUID id);

    CityDTO findByName(String name);

    CityDTO save(UUID countryId, BaseCityDTO cityDTO);

    void deleteById(UUID id);
}
