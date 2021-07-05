package com.exadel.discount.repository;

import com.exadel.discount.entity.Discount;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, UUID> {

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor"})
    List<Discount> findAll();

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor"})
    Optional<Discount> findById(UUID id);

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor"})
    List<Discount> findAllByArchivedTrue();

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor"})
    List<Discount> findAllByArchivedFalse();

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor"})
    Optional<Discount> findByIdAndArchivedFalse(UUID id);

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor"})
    Optional<Discount> findByIdAndArchivedTrue(UUID id);
}
