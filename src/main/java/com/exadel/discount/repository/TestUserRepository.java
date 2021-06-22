package com.exadel.discount.repository;

import com.exadel.discount.model.security.Role;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class TestUserRepository {
    public static com.exadel.discount.repository.User findByEmail(String email) {
        ArrayList<com.exadel.discount.repository.User> list = new ArrayList<>();
        list.add(new com.exadel.discount.repository.User(
                "admin@mail.com",
                "$2a$12$fDSF9o5Yoc.Pu5XmcmXcx.JYUgv/PwEUEJB.EX94PCjz1IOO7pIYK",
                Role.ADMIN));
        list.add(new com.exadel.discount.repository.User(
                "user@mail.com",
                "$2a$12$eJnv/OFi2baPDYN8uxxQiuoYN1iezTVx71bWXLo/cpuRHTW6mwbhy",
                Role.USER
        ));
        for (com.exadel.discount.repository.User user: list) {
            if (user.getEmail().equals(email))
                return user;
        }
        return null;
    }
}
