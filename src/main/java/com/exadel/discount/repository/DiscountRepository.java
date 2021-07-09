package com.exadel.discount.repository;

import com.exadel.discount.entity.Discount;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityResult;
import javax.persistence.SqlResultSetMapping;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, UUID>, QuerydslPredicateExecutor<Discount> {

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor",
            "vendorLocations.city", "vendorLocations.city.country"})
    List<Discount> findAll();

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor",
            "vendorLocations.city", "vendorLocations.city.country"})
    Optional<Discount> findById(UUID id);

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor",
            "vendorLocations.city", "vendorLocations.city.country"})
    List<Discount> findAll(Predicate predicate, Sort sort);

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor",
            "vendorLocations.city", "vendorLocations.city.country"})
    Page<Discount> findAll(Predicate predicate, Pageable pageable);

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor",
            "vendorLocations.city", "vendorLocations.city.country"})
    Optional<Discount> findByIdAndArchived(UUID id, boolean archived);

    @Modifying
    @Query("UPDATE Discount d SET d.archived=:archived WHERE d.id=:discountId")
    void setArchivedById(@Param("discountId") UUID id, @Param("archived") boolean archived);

    boolean existsByIdAndArchived(UUID id, boolean archived);

    boolean existsByIdAndArchivedAndVendorArchived(UUID id, boolean discountArchived, boolean vendorArchived);
}
