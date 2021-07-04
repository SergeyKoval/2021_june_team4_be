package com.exadel.discount.repository;

import com.exadel.discount.entity.Role;
import com.exadel.discount.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = {"city"})
    Page<User> findAll(Pageable paging);

    @EntityGraph(attributePaths = {"city"})
    Optional<User> findById(UUID id);

    @Query(value = "SELECT u FROM User u WHERE u.role = :r", countQuery = "SELECT count(u) FROM User u WHERE u.role = :r")
    Page<User> findUserByRole(@Param("r") Role r, Pageable pageable);

    @EntityGraph(attributePaths = {"city"})
    Page<User> findUsersByCity_Name(String city, Pageable pageable);

    @Query(value = "SELECT u FROM User u JOIN FETCH City c ON c.id = u.city JOIN FETCH Country ct ON c.country = ct.id WHERE ct.name = :countryFilter",
            countQuery = "SELECT count(u) FROM User u JOIN City c ON c.id = u.city JOIN Country ct ON c.country = ct.id WHERE ct.name = :countryFilter")
    Page<User> findUsersByCountry_Name(@Param("countryFilter")String country, Pageable pageable);

    List<User> findDistinctByLastNameAndFirstName(String lastName, String firstName);
}
