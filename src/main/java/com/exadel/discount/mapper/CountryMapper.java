package com.exadel.discount.mapper;

import com.exadel.discount.dto.CountryDTO;
import com.exadel.discount.entity.Country;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    Country parseDTO(CountryDTO countryDTO);

    CountryDTO getDTO(Country country);

    List<CountryDTO> getListDTO(List<Country> countries);
}