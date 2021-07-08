package com.exadel.discount.mapper;

import com.exadel.discount.dto.city.CityDTO;
import com.exadel.discount.dto.country.CountryDTO;
import com.exadel.discount.dto.user.UserDTO;
import com.exadel.discount.entity.City;
import com.exadel.discount.entity.Country;
import com.exadel.discount.entity.User;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CityMapper.class})
public interface UserMapper {
    UserDTO toUserDTO(User user);

    List<UserDTO> toUserDTOList(List<User> userList);
}
