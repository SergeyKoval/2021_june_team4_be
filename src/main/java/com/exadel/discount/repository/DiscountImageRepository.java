package com.exadel.discount.repository;

import java.util.UUID;

import com.exadel.discount.model.entity.DiscountImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountImageRepository extends JpaRepository<DiscountImage, UUID> {
    
}
