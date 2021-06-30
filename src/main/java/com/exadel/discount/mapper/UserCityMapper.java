package com.exadel.discount.mapper;

import com.exadel.discount.dto.user.UserCityDto;
import com.exadel.discount.entity.City;
import com.exadel.discount.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserCityMapper {

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "user.login", target = "login")
    @Mapping(source = "user.password", target = "password")
    @Mapping(source = "user.role", target = "role")
    @Mapping(source = "user.phone", target = "phone")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "city.name", target = "cityName")
    UserCityDto toUserCityDto(User user, City city);
}
