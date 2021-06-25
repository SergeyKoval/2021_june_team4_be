package com.exadel.discount.repository;

import com.exadel.discount.entity.City;
import com.exadel.discount.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CountryRepository extends JpaRepository<Country, UUID> {
}