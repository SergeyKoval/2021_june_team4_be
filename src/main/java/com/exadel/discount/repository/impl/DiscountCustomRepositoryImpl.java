package com.exadel.discount.repository.impl;

import com.exadel.discount.entity.QDiscount;
import com.exadel.discount.repository.DiscountCustomRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class DiscountCustomRepositoryImpl implements DiscountCustomRepository {

    private final JPAQueryFactory queryFactory;

    public DiscountCustomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

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
