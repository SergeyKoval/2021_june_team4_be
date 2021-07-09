//package com.exadel.discount.repository.query;
//
//import static org.springframework.data.querydsl.QueryDslUtils.QUERY_DSL_PRESENT;
//
//import javax.persistence.EntityManager;
//
//import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
//import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
//import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
//import org.springframework.data.querydsl.QueryDslPredicateExecutor;
//import org.springframework.data.repository.Repository;
//import org.springframework.data.repository.core.RepositoryMetadata;
//
//public class CustomJpaRepositoryFactory extends JpaRepositoryFactory {
//    public CustomJpaRepositoryFactory(EntityManager entityManager) {
//        super(entityManager);
//    }
//
//    @Override
//    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
//        if(QUERY_DSL_PRESENT) {
//            Class<?> repositoryInterface = metadata.getRepositoryInterface();
//            if(CustomQueryDslPredicateExecutor.class.isAssignableFrom(repositoryInterface)) {
//                return CustomQueryDslJpaRepository.class;
//            } else  if(QueryDslPredicateExecutor.class.isAssignableFrom(repositoryInterface)) {
//                return QueryDslJpaRepository.class;
//            }
//        }
//        return SimpleJpaRepository.class;
//    }
//}