//package com.exadel.discount.repository.query;
//
//import com.exadel.discount.entity.Coupon;
//import com.exadel.discount.entity.QCoupon;
//import com.querydsl.core.types.Predicate;
//import com.querydsl.jpa.JPQLQuery;
//import liquibase.pro.packaged.M;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.querydsl.QuerydslPredicateExecutor;
//
//import java.util.List;
//
//public class CouponRepositoryImpl  implements QuerydslPredicateExecutor<Coupon>, CouponRepositoryCustom {
//
//    CouponRepositoryImpl() {
//        super(Coupon.class);
//    }
//
//    public Page<Coupon> findAll(Predicate predicate, Pageable pageable) {
//
//        QCoupon coupon = QCoupon.coupon;
//
//        JPQLQuery<Coupon> query = from(coupon);
//
//        if (StringUtils.isNotEmpty(name)) {
//            BooleanBuilder searchCondition = new BooleanBuilder();
//
//            searchCondition.and(Coupon.name.eq(name));
//
//            if (searchCondition.hasValue()) {
//                query.where(searchCondition);
//            }
//        }
//
//        long totalFound = query.fetchCount();
//        List<Coupon> results = query.select(quiz).fetch();
//        return new PageImpl<M>(results, pageable, totalFound);
//    }
//
//}
