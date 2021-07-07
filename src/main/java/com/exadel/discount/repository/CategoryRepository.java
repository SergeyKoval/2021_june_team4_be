package com.exadel.discount.repository;

import com.exadel.discount.entity.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @EntityGraph(attributePaths = {"discounts"})
    Optional<Category> findById(UUID uuid);
}
