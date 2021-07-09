package com.exadel.discount.repository;

import com.exadel.discount.entity.Role;
import com.exadel.discount.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @EntityGraph(attributePaths = {"city", "city.country"})
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = {"city", "city.country"})
    Page<User> findAll(Pageable paging);

    @EntityGraph(attributePaths = {"city", "city.country"})
    Optional<User> findById(UUID id);

    @EntityGraph(attributePaths = {"city", "city.country"})
    Page<User> findByRole(Role r, Pageable pageable);

    @EntityGraph(attributePaths = {"city", "city.country"})
    Page<User> findByCityName(String city, Pageable pageable);

    @EntityGraph(attributePaths = {"city", "city.country"})
    Page<User> findUsersByCityCountryName(String country, Pageable pageable);

    @EntityGraph(attributePaths = {"city", "city.country"})
    List<User> findDistinctByLastNameAndFirstName(String lastName, String firstName);
}
