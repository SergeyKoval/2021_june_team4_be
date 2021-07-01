package com.exadel.discount.repository;

import com.exadel.discount.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, UUID> {
    @Query(nativeQuery = true,
            value="select * from cities where country_id = (select id from countries where countries.name = ?1)")
    List<City> findByCountry(String name);

    Optional<City> findByName(String name);
}
