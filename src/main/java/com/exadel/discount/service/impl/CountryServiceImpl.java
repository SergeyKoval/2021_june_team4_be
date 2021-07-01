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
    public List<CountryDTO> findAll() {
        log.debug("Getting list of all Countries");

        List<CountryDTO> countryDTOList = countryMapper.getListDTO(countryRepository.findAll());

        log.debug("Successfully got list of all Countries");
        return countryDTOList;
    }

    @Override
    public CountryDTO findById(UUID id) {
        log.debug("Finding Country by Id");

        CountryDTO countryDTO = countryMapper.countryToCountryDTO(countryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Country with id %s not found", id))));

        log.debug("Country successfully found by Id");
        return countryDTO;
    }

    @Override
    public CountryDTO findByName(String name) {
        log.debug("Finding Country by Name");

        CountryDTO countryDTO = countryMapper.countryToCountryDTO(countryRepository.findByName(name));

        log.debug("Country successfully found by Name");
        return countryDTO;
    }

    @Override
    public CountryDTO save(CountryDTO countryDTO) {
        log.debug("Saving new Country");

        Country newCountry = countryRepository.save(countryMapper.countryDTOToCountry(countryDTO));

        log.debug("Successfully saved new Country");
        return countryMapper.countryToCountryDTO(newCountry);
    }

    @Override
    public void deleteById(UUID id) {
        log.debug("Deleting Country");
        countryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Cannot delete. Country with Id %s not found", id)));
        countryRepository.deleteById(id);

        log.debug("Country successfully deleted");
    }
}
