package com.exadel.discount.repository;

import com.exadel.discount.entity.VendorLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendoLocationrRepository extends JpaRepository<VendorLocation, Long> {
}
