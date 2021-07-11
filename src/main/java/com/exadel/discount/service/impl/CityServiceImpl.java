package com.exadel.discount.service.impl;

import com.exadel.discount.dto.city.BaseCityDTO;
import com.exadel.discount.dto.city.CityDTO;
import com.exadel.discount.entity.City;
import com.exadel.discount.entity.Country;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.CityMapper;
import com.exadel.discount.repository.CityRepository;
import com.exadel.discount.repository.CountryRepository;
import com.exadel.discount.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final CountryRepository countryRepository;

    @Override
    public List<CityDTO> findAll() {
        log.debug("Getting list of all Cities");
        List<CityDTO> cityDTOList = cityMapper.getListDTO(cityRepository.findAll());
        log.debug("Successfully got list of all Cities");
        return cityDTOList;
    }

    @Override
    public List<CityDTO> findAllByCountryName(String countryName) {
        log.debug("Getting list of all Cities by CountryName");
        List<CityDTO> cityDTOList = cityMapper.getListDTO(cityRepository.findByCountryName(countryName));
        log.debug("Successfully got list of all Cities by CountryName");
        return cityDTOList;
    }

    @Override
    public List<CityDTO> findAllByCountryId(UUID countryId) {
        log.debug("Getting list of all Cities by CountryId");
        List<CityDTO> cityDTOList = cityMapper.getListDTO(cityRepository.findByCountryId(countryId));
        log.debug("Successfully got list of all Cities by CountryId");
        return cityDTOList;
    }

    @Override
    public CityDTO findById(UUID id) {
        log.debug("Finding City by Id");
        City city = cityRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("City with id %s not found", id)));
        log.debug("City successfully found by Id");
        return cityMapper.cityToCityDTO(city);
    }

    @Override
    public CityDTO findByName(String name) {
        log.debug("Finding City by Name");
        City city = cityRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException(String.format("City with name %s not found", name)));
        log.debug("City successfully found by Name");
        return cityMapper.cityToCityDTO(city);
    }

    @Override
    public CityDTO save(UUID countryId, BaseCityDTO cityDTO) {
        log.debug("Saving new City");
        Country country = countryRepository
                .findById(countryId)
                .orElseThrow(() -> new NotFoundException(String.format("Country with id %s doesn't exist", countryId)));
        City city = cityMapper.baseCityDTOToCity(cityDTO);
        city.setCountry(country);
        City newCity = cityRepository.save(city);
        log.debug("Successfully saved new City");
        return cityMapper.cityToCityDTO(newCity);
    }

    @Override
    public void deleteById(UUID id) {
        log.debug("Deleting City");
        cityRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("City with ID %s not found", id)));
        cityRepository.deleteById(id);
        log.debug("City successfully deleted");
    }
}
