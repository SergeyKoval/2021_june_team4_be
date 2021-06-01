package com.exadel.discount.repository;

import com.exadel.discount.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findDistinctByLastNameAndFirstName(String lastName, String firstName);

    void deleteById(UUID id);

    default User findUserById(UUID userId) {
        return findById(userId).get();
    }
}
