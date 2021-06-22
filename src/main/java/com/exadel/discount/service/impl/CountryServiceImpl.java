package com.exadel.discount.service.impl;

import com.exadel.discount.dto.CountryDTO;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.CountryMapper;
import com.exadel.discount.repository.CountryRepository;
import com.exadel.discount.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public List<CountryDTO> findAll() {
        return countryMapper.getListDTO(countryRepository.findAll());
    }

    @Override
    public CountryDTO findOneById(UUID id) {

        return countryMapper.getDTO(countryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Country with id %s not found", id))));
    }

    @Override
    public CountryDTO findByName(String name) {

        return countryMapper.getDTO(countryRepository.findAll()
                .stream()
                .filter(s -> s.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Country with name %s not found", name))));
    }

    @Override
    public CountryDTO save(CountryDTO countryDTO) {

        return countryMapper.getDTO(countryRepository.save(countryMapper.parseDTO(countryDTO)));
    }

    @Override
    public void deleteById(UUID id) {

        countryRepository.deleteById(id);
    }
}
