package com.exadel.discount.service;

import com.exadel.discount.entity.Coupon;
import com.exadel.discount.entity.User;

import java.util.List;
import java.util.UUID;


public interface UserService {
    User findUserById(UUID id);

    User addUser(User user);

    List<User> deleteUser(UUID id);

    List<User> findAllUsers();

    List<User> findUsersByName(String name);

    User editUser(UUID id, User user);

    User addNewCouponToUser(Coupon coupon, UUID userId);

    User removeCouponFromUser(UUID userId, Coupon coupon);
}


