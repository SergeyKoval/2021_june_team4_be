//package com.exadel.discount.repository.query;
//
//import com.querydsl.core.types.FactoryExpression;
//import com.querydsl.core.types.Predicate;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.querydsl.QueryDslPredicateExecutor;
//
//public interface CustomQueryDslPredicateExecutor<T> extends QueryDslPredicateExecutor<T> {
//    <DTO> Page<DTO> findAll(Predicate predicate, Pageable pageable, FactoryExpression<DTO> factoryExpression);
//}