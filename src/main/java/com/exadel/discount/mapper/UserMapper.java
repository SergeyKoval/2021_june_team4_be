package com.exadel.discount.mapper;

import com.exadel.discount.dto.user.UserDto;
import com.exadel.discount.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User User);

    List<UserDto> toUserDtoList(List<User> users);
}
