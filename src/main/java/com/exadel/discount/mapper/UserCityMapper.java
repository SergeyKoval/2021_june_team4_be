package com.exadel.discount.mapper;

import com.exadel.discount.dto.CityDTO;
import com.exadel.discount.dto.CountryDTO;
import com.exadel.discount.dto.user.UserCityDto;
import com.exadel.discount.dto.user.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserCityMapper {
    @Mapping(source = "userDto.id", target = "id")
    @Mapping(source = "userDto.firstName", target = "firstName")
    @Mapping(source = "userDto.lastName", target = "lastName")
    @Mapping(source = "userDto.login", target = "login")
    @Mapping(source = "userDto.password", target = "password")
    @Mapping(source = "userDto.role", target = "role")
    @Mapping(source = "userDto.phone", target = "phone")
    @Mapping(source = "userDto.email", target = "email")
    @Mapping(source = "cityDTO.name", target = "cityName")
    @Mapping(source = "countryDTO.name", target = "country")
    UserCityDto toUserCityDto(UserDto userDto, CityDTO cityDTO, CountryDTO countryDTO);
}
