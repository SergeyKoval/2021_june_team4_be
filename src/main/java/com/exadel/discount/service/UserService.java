package com.exadel.discount.service;

import com.exadel.discount.entity.User;

import java.util.List;
import java.util.UUID;


public interface UserService {
    User findUserById(UUID id);

    User addUser(User user);

    List<User> deleteUser(UUID id);

    List<User> findAllUsers();

    List<User> findUsersByName(String lastName, String firstName);

    User editUser(UUID id, User user);

}


