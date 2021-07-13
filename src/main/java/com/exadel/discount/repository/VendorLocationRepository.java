package com.exadel.discount.repository;

import com.exadel.discount.model.entity.VendorLocation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface VendorLocationRepository extends JpaRepository<VendorLocation, UUID> {

    @EntityGraph(attributePaths = {"city", "city.country"})
    List<VendorLocation> findByVendorId(UUID vendorId);

    @EntityGraph(attributePaths = {"city", "city.country"})
    List<VendorLocation> findByIdInAndVendorId(Collection<UUID> locationIds, UUID vendorId);

    boolean existsById(UUID id);

    @EntityGraph(attributePaths = {"city", "city.country"})
    VendorLocation save(VendorLocation vendorLocation);
}
