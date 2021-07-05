package com.exadel.discount.repository;

import com.exadel.discount.entity.Vendor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, UUID> {

    @EntityGraph(attributePaths = {"vendorLocations"})
    List<Vendor> findAll();

    @EntityGraph(attributePaths = {"vendorLocations"})
    Optional<Vendor> findById(UUID id);
}
