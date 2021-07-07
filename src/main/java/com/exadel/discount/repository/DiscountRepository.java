package com.exadel.discount.repository;

import com.exadel.discount.entity.Discount;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, UUID>, QuerydslPredicateExecutor<Discount> {

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor"})
    List<Discount> findAll();

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor"})
    Optional<Discount> findById(UUID id);

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor"})
    Optional<Discount> findByIdAndArchived(UUID id, boolean archived);

    @EntityGraph(attributePaths = {"category", "vendorLocations", "tags", "vendor"})
    Page<Discount> findAll(Predicate predicate, Pageable pageable);
}
