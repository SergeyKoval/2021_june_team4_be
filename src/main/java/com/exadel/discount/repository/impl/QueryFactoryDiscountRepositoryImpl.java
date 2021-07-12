package com.exadel.discount.repository.impl;

import com.exadel.discount.entity.QDiscount;
import com.exadel.discount.repository.QueryFactoryDiscountRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class QueryFactoryDiscountRepositoryImpl implements QueryFactoryDiscountRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<UUID> findAllDiscountIds(Predicate predicate, Pageable pageable) {
        QDiscount qDiscount = QDiscount.discount;
        return queryFactory
                .select(qDiscount.id)
                .distinct()
                .from(qDiscount)
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
