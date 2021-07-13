package com.exadel.discount.model.dto.mapper;

import com.exadel.discount.model.dto.user.UserDTO;
import com.exadel.discount.model.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CityMapper.class})
public interface UserMapper {

    UserDTO toUserDTO (User user);

    List<UserDTO> toUserDTOList (List<User> userList);
}
