package com.exadel.discount.repository.impl;

import com.exadel.discount.model.entity.QFavorite;
import com.exadel.discount.repository.QueryFactoryFavoriteRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class QueryFactoryFavoriteRepositoryImpl implements QueryFactoryFavoriteRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<UUID> findAllFavoriteIds(Predicate predicate, Pageable pageable) {
        QFavorite qFavorite = QFavorite.favorite;
        return queryFactory
                .select(qFavorite.id)
                .distinct()
                .from(qFavorite)
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}

