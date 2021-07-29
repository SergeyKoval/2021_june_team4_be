package com.exadel.discount.repository;

import com.exadel.discount.model.entity.City;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, UUID> {

    @EntityGraph(attributePaths = {"country"})
    Optional<City> findById(UUID id);

    @EntityGraph(attributePaths = {"country"})
    List<City> findByCountryName(String name);

    @EntityGraph(attributePaths = {"country"})
    List<City> findByCountryId(UUID uuid);

    @EntityGraph(attributePaths = {"country"})
    Optional<City> findByName(String name);
}
