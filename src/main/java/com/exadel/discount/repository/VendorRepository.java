package com.exadel.discount.repository;

import com.exadel.discount.entity.Vendor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @EntityGraph(attributePaths = {"vendorLocations"})
    List<Vendor> findAllByArchived(boolean archived);

    @EntityGraph(attributePaths = {"vendorLocations"})
    Optional<Vendor> findByIdAndArchived(UUID id, boolean archived);

    @Modifying
    @Query("UPDATE Vendor v SET v.archived=true WHERE v.id=:vendorId")
    void archiveById(@Param("vendorId") UUID id);

    @Modifying
    @Query("UPDATE Vendor v SET v.archived=false WHERE v.id=:vendorId")
    void restoreById(@Param("vendorId") UUID id);

    @Query("SELECT count(v)>0 FROM Vendor v WHERE v.id=:vendorId AND v.archived=false AND" +
            "(SELECT count(d) FROM Discount d WHERE d.vendor.id=:vendorId AND d.archived=false) = 0")
    boolean existsByIdWithNoDiscounts(@Param("vendorId") UUID vendorId);

    boolean existsByIdAndArchived(UUID id, boolean archived);
}
