package com.exadel.discount.service;

import com.exadel.discount.dto.user.UserCityDto;
import com.exadel.discount.dto.user.UserDto;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;


public interface UserService {
    UserCityDto findUserById(UUID id);

    List<UserCityDto> findAllUsers(int pageNumber, int pageSize, Sort sort, String cityFilter, String countryFilter, String roleFilter);

    List<UserDto> findUsersByName(String lastName, String firstName);
}
