package com.exadel.discount.repository.impl;

import com.exadel.discount.model.entity.QCoupon;
import com.exadel.discount.repository.QueryFactoryCouponRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class QueryFactoryCouponRepositoryImpl implements QueryFactoryCouponRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<UUID> findAllCouponIds(Predicate predicate, Pageable pageable) {
        QCoupon qCoupon = QCoupon.coupon;
        return queryFactory
                .select(qCoupon.id)
                .distinct()
                .from(qCoupon)
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
