package com.exadel.discount.repository;

import com.exadel.discount.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CityRepository extends JpaRepository<City, UUID> {
}
