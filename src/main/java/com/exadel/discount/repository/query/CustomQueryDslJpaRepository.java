//package com.exadel.discount.repository.query;
//
//import java.io.Serializable;
//
//import javax.persistence.EntityManager;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.support.JpaEntityInformation;
//import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
//import org.springframework.data.jpa.repository.support.Querydsl;
//import org.springframework.data.querydsl.EntityPathResolver;
//import org.springframework.data.querydsl.SimpleEntityPathResolver;
//
//import com.querydsl.core.QueryResults;
//import com.querydsl.core.types.EntityPath;
//import com.querydsl.core.types.FactoryExpression;
//import com.querydsl.core.types.Predicate;
//import com.querydsl.core.types.Projections;
//import com.querydsl.core.types.dsl.PathBuilder;
//import com.querydsl.jpa.JPQLQuery;
//
//public class CustomQueryDslJpaRepository<T, ID extends Serializable> extends QueryDslJpaRepository<T, ID> implements CustomQueryDslPredicateExecutor<T> {
//    private static final EntityPathResolver DEFAULT_ENTITY_PATH_RESOLVER = SimpleEntityPathResolver.INSTANCE;
//
//    private final Querydsl querydsl;
//
//    public CustomQueryDslJpaRepository(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
//        this(entityInformation, entityManager, DEFAULT_ENTITY_PATH_RESOLVER);
//    }
//
//    public CustomQueryDslJpaRepository(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager, EntityPathResolver resolver) {
//        super(entityInformation, entityManager, resolver);
//
//        EntityPath<T> path = resolver.createPath(entityInformation.getJavaType());
//        PathBuilder<T> builder = new PathBuilder<T>(path.getType(), path.getMetadata());
//        this.querydsl = new Querydsl(entityManager, builder);
//    }
//
//    public <DTO> Page<DTO> findAll(Predicate predicate, Pageable pageable, FactoryExpression<DTO> factoryExpression) {
//        JPQLQuery<DTO> query = createQuery(predicate).select(factoryExpression);
//        querydsl.applyPagination(pageable, query);
//        querydsl.applySorting(pageable.getSort(), query);
//
//        QueryResults<DTO> queryResults = query.fetchResults();
//        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
//    }
//}