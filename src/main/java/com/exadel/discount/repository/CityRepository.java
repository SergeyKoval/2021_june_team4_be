package com.exadel.discount.repository;

import com.exadel.discount.entity.City;
import com.exadel.discount.entity.Coupon;
import com.exadel.discount.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, UUID> {
    Optional<City> findByName(String name);
    List<City> findAllByCountry_Name(String country);
}
