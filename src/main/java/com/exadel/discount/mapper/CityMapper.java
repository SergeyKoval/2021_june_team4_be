package com.exadel.discount.mapper;

import com.exadel.discount.dto.CityDTO;
import com.exadel.discount.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    City cityDTOToCity(CityDTO cityDTO);

    @Mapping(expression = "java(city.getCountry().getId())", target = "countryId")
    CityDTO cityToCityDTO(City city);

    List<CityDTO> getListDTO(List<City> cities);
}
