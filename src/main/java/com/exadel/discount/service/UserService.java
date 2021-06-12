package com.exadel.discount.service;

import com.exadel.discount.entity.User;

import java.util.List;
import java.util.UUID;


public interface UserService {
    User findUserById(UUID id);

    User addUser(User user);

    User deleteUser(UUID id);

    List<User> findAllUsers();

    List<User> findUsersByName(String name);

    User editUser(UUID id, User user);

    User addCouponToUser(UUID couponId, UUID userId);

    User removeCouponFromUser(UUID couponId, UUID userId);
}


