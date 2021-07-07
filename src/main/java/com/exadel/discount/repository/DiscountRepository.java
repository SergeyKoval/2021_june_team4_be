package com.exadel.discount.repository;

import com.exadel.discount.entity.Discount;
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
public interface DiscountRepository extends JpaRepository<Discount, UUID> {

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor"})
    List<Discount> findAll();

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor"})
    Optional<Discount> findById(UUID id);

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor"})
    List<Discount> findAllByArchived(boolean archived);

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor"})
    Optional<Discount> findByIdAndArchived(UUID id, boolean archived);

    @Modifying
    @Query("UPDATE Discount d SET d.archived=:archived WHERE d.id=:discountId")
    void setArchivedById(@Param("discountId") UUID id, @Param("archived") boolean archived);

    boolean existsByIdAndArchived(UUID id, boolean archived);

    boolean existsByIdAndArchivedAndVendorArchived(UUID id, boolean discountArchived, boolean vendorArchived);
}
