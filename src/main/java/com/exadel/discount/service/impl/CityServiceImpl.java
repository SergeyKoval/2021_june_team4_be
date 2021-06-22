package com.exadel.discount.service.impl;

import com.exadel.discount.dto.CityDTO;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.CityMapper;
import com.exadel.discount.repository.CityRepository;
import com.exadel.discount.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public List<CityDTO> findAll() {

        return cityMapper.getListDTO(cityRepository.findAll());
    }

    @Override
    public CityDTO findOneById(UUID id) {

        return cityMapper.getDTO(cityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("City with id %s not found", id))));
    }

    @Override
    public CityDTO findByName(String name) {
        return cityMapper.getDTO(cityRepository.findAll()
                .stream()
                .filter(s -> s.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("City with name %s not found", name))));
    }

    @Override
    public CityDTO save(CityDTO cityDTO) {

        return cityMapper.getDTO(cityRepository.save(cityMapper.parseDTO(cityDTO)));
    }

    @Override
    public void deleteById(UUID id) {

        cityRepository.deleteById(id);
    }
}
