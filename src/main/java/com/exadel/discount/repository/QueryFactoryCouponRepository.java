package com.exadel.discount.repository;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QueryFactoryCouponRepository {

    List<UUID> findAllCouponIds(Predicate predicate, Pageable pageable);
}
