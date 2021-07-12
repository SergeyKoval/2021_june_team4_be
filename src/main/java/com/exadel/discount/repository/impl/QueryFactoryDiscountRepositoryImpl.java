package com.exadel.discount.repository.impl;

import com.exadel.discount.entity.QDiscount;
import com.exadel.discount.repository.QueryFactoryDiscountRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
public class QueryFactoryDiscountRepositoryImpl implements QueryFactoryDiscountRepository {

    private final JPAQueryFactory queryFactory;

    public QueryFactoryDiscountRepositoryImpl(EntityManager em) {
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
