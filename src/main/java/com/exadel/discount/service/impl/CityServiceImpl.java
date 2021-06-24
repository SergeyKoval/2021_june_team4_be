package com.exadel.discount.service.impl;

import com.exadel.discount.dto.CityDTO;
import com.exadel.discount.entity.City;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.CityMapper;
import com.exadel.discount.repository.CityRepository;
import com.exadel.discount.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public List<CityDTO> findAllCities() {
        log.debug("Getting list of all Cities");

        List<CityDTO> cityDTOList = cityMapper.getListDTO(cityRepository.findAll());

        log.debug("Successfully got list of all Cities");
        return cityDTOList;
    }

    @Override
    public List<CityDTO> findAllCitiesByCountryId(UUID id) {
        log.debug("Getting list of all Cities by CountryId");

        List<CityDTO> cityDTOList = cityMapper.getListDTO(cityRepository.findAll()
                .stream()
                .filter(s -> s.getCountry().getId().equals(id))
                .collect(Collectors.toList()));

        log.debug("Successfully got list of all Cities by CountryId");
        return cityDTOList;
    }

    @Override
    public CityDTO findCityById(UUID id) {
        log.debug("Finding City by Id");

        CityDTO cityDTO = cityMapper.getDTO(cityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("City with id %s not found", id))));

        log.debug("City successfully found by Id");
        return cityDTO;
    }

    @Override
    public CityDTO findCityByName(String name) {
        log.debug("Finding City by Name");

        CityDTO cityDTO = cityMapper.getDTO(cityRepository.findAll()
                .stream()
                .filter(s -> s.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("City with name %s not found", name))));

        log.debug("City successfully found by Name");
        return cityDTO;
    }

    @Override
    public CityDTO save(CityDTO cityDTO) {
        log.debug("Saving new City");

        City newCity = cityRepository.save(cityMapper.parseDTO(cityDTO));

        log.debug("Successfully saved new City");
        return cityMapper.getDTO(newCity);
    }

    @Override
    public void deleteById(UUID id) {
        log.debug("Deleting City");
        try {
            cityRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException(String.format("Cannot delete. City with Id %s not found", id));
        }

        log.debug("City successfully deleted");
    }
}
