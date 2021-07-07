package com.exadel.discount.mapper;

import com.exadel.discount.dto.user.UserDTO;
import com.exadel.discount.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected CountryMapper countryMapper;
    @Autowired
    protected  CityMapper cityMapper;

    @Mapping(expression = "java(countryMapper.countryToCountryDTO(user.getCity().getCountry()))", target = "countryDTO")
    @Mapping(expression = "java(cityMapper.cityToCityDTO(user.getCity()))", target = "cityDTO")
    public abstract UserDTO toUserDTO(User user);

    public abstract List<UserDTO> toUserDTOList(List<User> userList);
}
