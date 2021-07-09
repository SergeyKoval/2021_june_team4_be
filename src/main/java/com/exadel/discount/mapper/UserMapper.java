package com.exadel.discount.mapper;

import com.exadel.discount.dto.user.UserDTO;
import com.exadel.discount.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CityMapper.class})
public interface UserMapper {

    UserDTO toUserDTO (User user);

    List<UserDTO> toUserDTOList (List<User> userList);
}
