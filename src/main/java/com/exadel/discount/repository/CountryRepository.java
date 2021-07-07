package com.exadel.discount.repository;

import com.exadel.discount.entity.Country;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CountryRepository extends JpaRepository<Country, UUID> {

    @EntityGraph(attributePaths = {"cities"})
    List<Country> findAll();

    @EntityGraph(attributePaths = {"cities"})
    Optional<Country> findById(UUID id);

    @EntityGraph(attributePaths = {"cities"})
    Optional<Country> findByName(String name);
}
