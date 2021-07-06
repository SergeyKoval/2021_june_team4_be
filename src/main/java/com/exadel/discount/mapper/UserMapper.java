package com.exadel.discount.mapper;

import com.exadel.discount.dto.city.CityDTO;
import com.exadel.discount.dto.country.CountryDTO;
import com.exadel.discount.dto.user.UserDTO;
import com.exadel.discount.entity.City;
import com.exadel.discount.entity.Country;
import com.exadel.discount.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", imports = CountryMapper.class)
public abstract class UserMapper {

    @Autowired
    protected CountryMapper countryMapper;

    @Mapping(expression = "java(countryMapper.countryToCountryDTO(user.getCity().getCountry()))", target = "countryDTO")
    public abstract UserDTO toUserDTO(User user);

    @Mapping(expression = "java(city.getCountry().getId())", target = "countryId")
    public abstract CityDTO cityToCityDTO(City city);

    public abstract List<UserDTO> toUserDTOList(List<User> userList);
}
