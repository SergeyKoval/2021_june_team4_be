package com.exadel.discount.service;

import com.exadel.discount.dto.user.UserDto;

import java.util.List;
import java.util.UUID;


public interface UserService {
    UserDto findUserById(UUID id);

    List<UserDto> findAllUsers();

    List<UserDto> findUsersByName(String lastName, String firstName);
}


