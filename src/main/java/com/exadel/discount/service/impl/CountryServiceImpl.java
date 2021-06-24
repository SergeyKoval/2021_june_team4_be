package com.exadel.discount.service.impl;

import com.exadel.discount.dto.CountryDTO;
import com.exadel.discount.entity.Country;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.CountryMapper;
import com.exadel.discount.repository.CountryRepository;
import com.exadel.discount.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public List<CountryDTO> findAllCountries() {
        log.debug("Getting list of all Countries");

        List<CountryDTO> countryDTOList = countryMapper.getListDTO(countryRepository.findAll());

        log.debug("Successfully got list of all Countries");
        return countryDTOList;
    }

    @Override
    public CountryDTO findCountryById(UUID id) {
        log.debug("Finding Country by Id");

        CountryDTO countryDTO = countryMapper.getDTO(countryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Country with id %s not found", id))));

        log.debug("Country successfully found by Id");
        return countryDTO;
    }

    @Override
    public CountryDTO findCountryByName(String name) {
        log.debug("Finding Country by Name");

        CountryDTO countryDTO = countryMapper.getDTO(countryRepository.findAll()
                .stream()
                .filter(s -> s.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Country with name %s not found", name))));

        log.debug("Country successfully found by Name");
        return countryDTO;
    }

    @Override
    public CountryDTO save(CountryDTO countryDTO) {
        log.debug("Saving new Country");

        Country newCountry = countryRepository.save(countryMapper.parseDTO(countryDTO));

        log.debug("Successfully saved new Country");
        return countryMapper.getDTO(newCountry);
    }

    @Override
    public void deleteById(UUID id) {
        log.debug("Deleting Country");

        try {
            countryRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException(String.format("Cannot delete. Country with Id %s not found", id));
        }
        log.debug("Country successfully deleted");
    }
}
