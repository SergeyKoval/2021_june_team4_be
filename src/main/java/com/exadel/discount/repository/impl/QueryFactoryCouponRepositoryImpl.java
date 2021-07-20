package com.exadel.discount.repository.impl;

import com.exadel.discount.model.entity.QCoupon;
import com.exadel.discount.repository.QueryFactoryCouponRepository;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        JPQLQuery<UUID> query = queryFactory
                .select(qCoupon.id)
                .from(qCoupon)
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        pageable.getSort()
                .get()
                .forEach(order -> query.orderBy(getSortedColumn(order, order.getProperty())));
        return query.fetch();
    }

    private OrderSpecifier<?> getSortedColumn(Sort.Order order, String fieldName) {
        Path<Object> fieldPath = Expressions.path(Object.class, QCoupon.coupon, fieldName);
        return new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC,
                fieldPath);
    }
}