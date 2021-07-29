package com.exadel.discount.repository;

import com.exadel.discount.model.entity.User;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, QuerydslPredicateExecutor<User> {
    @EntityGraph(attributePaths = {"city", "city.country"})
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = {"city", "city.country"})
    Page<User> findAll(Predicate predicate, Pageable paging);

    @EntityGraph(attributePaths = {"city", "city.country"})
    Page<User> findAll(Pageable paging);

    @EntityGraph(attributePaths = {"city", "city.country"})
    Optional<User> findById(UUID id);
}
