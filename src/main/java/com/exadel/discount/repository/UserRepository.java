package com.exadel.discount.repository;

import com.exadel.discount.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    <Optional> User findByEmail(String email);
}
