package com.exadel.discount.service;

import com.exadel.discount.dto.user.UserDTO;
import com.exadel.discount.dto.user.UserFilter;

import java.util.List;
import java.util.UUID;


public interface UserService {
    UserDTO findUserById(UUID id);

    List<UserDTO> findAllUsers(int pageNumber, int pageSize, String sortDirection, String sortField, UserFilter userfilter);

    List<UserDTO> findUsersByName(String lastName, String firstName);
}
