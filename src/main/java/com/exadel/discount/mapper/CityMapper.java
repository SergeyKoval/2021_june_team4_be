package com.exadel.discount.mapper;

import com.exadel.discount.dto.CityDTO;
import com.exadel.discount.entity.City;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    City parseDTO(CityDTO cityDTO);

    CityDTO getDTO(City city);

    List<CityDTO> getListDTO(List<City> cities);
}
