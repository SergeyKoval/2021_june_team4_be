package com.exadel.discount.service;

import com.exadel.discount.dto.user.UserDTO;


import java.util.List;
import java.util.UUID;


public interface UserService {
    UserDTO findUserById(UUID id);

    List<UserDTO> findAllUsers(int pageNumber, int pageSize, String sortDirection, String sortField);
    List<UserDTO> findUsersByRole(int pageNumber, int pageSize, String sortDirection, String sortField, String roleFilter);
    List<UserDTO> findUsersOfCity(int pageNumber, int pageSize, String sortDirection, String sortField, String cityFilter);
    List<UserDTO> findUsersOfCountry(int pageNumber, int pageSize, String sortDirection, String sortField, String countryFilter);

    List<UserDTO> findUsersByName(String lastName, String firstName);
}
