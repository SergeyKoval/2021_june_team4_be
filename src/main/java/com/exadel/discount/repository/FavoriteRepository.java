package com.exadel.discount.repository;

import com.exadel.discount.entity.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, UUID> {
   // List<Favorite> findByUserId(UUID id);
    Page<Favorite> findByUserId(UUID userId, Pageable pging);
}
