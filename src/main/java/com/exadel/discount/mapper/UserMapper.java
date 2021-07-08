package com.exadel.discount.mapper;

import com.exadel.discount.dto.user.UserDTO;
import com.exadel.discount.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "city.id", target = "cityDTO.id")
    @Mapping(source = "city.name", target = "cityDTO.name")
    @Mapping(source = "city.country.id", target = "cityDTO.countryId")
    @Mapping(source = "city.country.name", target = "cityDTO.countryName")
    UserDTO toUserDTO(User user);

    List<UserDTO> toUserDTOList(List<User> userList);
}
