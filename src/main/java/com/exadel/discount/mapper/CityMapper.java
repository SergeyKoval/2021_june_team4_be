package com.exadel.discount.mapper;

import com.exadel.discount.dto.city.BaseCityDto;
import com.exadel.discount.dto.city.CityDTO;
import com.exadel.discount.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    City cityDTOToCity(CityDTO cityDTO);

    @Mapping(source = "country.id", target = "countryId")
    @Mapping(source = "country.name", target = "countryName")
    CityDTO cityToCityDTO(City city);

    List<CityDTO> getListDTO(List<City> cities);

    BaseCityDto cityToBaseCityDTO(City city);

    List<BaseCityDto> getListBaseDTO(List<City> cities);
}
