package com.exadel.discount.service;

import com.exadel.discount.dto.user.UserCityDto;
import com.exadel.discount.dto.user.UserDto;

import java.util.List;
import java.util.UUID;


public interface UserService {
    UserCityDto findUserById(UUID id);

    List<UserCityDto> findAllUsers(int pageNumber, int pageSize, String sortDirection, String sortField);
    List<UserCityDto> findUsersByRole(int pageNumber, int pageSize, String sortDirection, String sortField, String roleFilter);
    List<UserCityDto> findUsersOfCity(int pageNumber, int pageSize, String sortDirection, String sortField, String cityFilter);
    List<UserCityDto> findUsersOfCountry(int pageNumber, int pageSize, String sortDirection, String sortField, String countryFilter);

    List<UserDto> findUsersByName(String lastName, String firstName);
}
