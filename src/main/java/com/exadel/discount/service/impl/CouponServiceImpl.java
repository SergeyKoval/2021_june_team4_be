package com.exadel.discount.service.impl;

import com.exadel.discount.model.dto.coupon.CouponDTO;
import com.exadel.discount.model.dto.coupon.CouponFilter;
import com.exadel.discount.model.dto.coupon.CreateCouponDTO;
import com.exadel.discount.model.dto.mapper.CouponMapper;
import com.exadel.discount.model.entity.Coupon;
import com.exadel.discount.model.entity.Discount;
import com.exadel.discount.model.entity.QCoupon;
import com.exadel.discount.model.entity.User;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.repository.CouponRepository;
import com.exadel.discount.repository.DiscountRepository;
import com.exadel.discount.repository.UserRepository;
import com.exadel.discount.repository.query.QueryPredicateBuilder;
import com.exadel.discount.service.CouponService;
import com.exadel.discount.service.SortPageMaker;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import liquibase.pro.packaged.S;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;
    private final UserRepository userRepository;
    private final DiscountRepository discountRepository;

    @Override
    public List<CouponDTO> findAllCoupons(int pageNumber, int pageSize, String sortDirection, String sortField,
                                          CouponFilter couponFilter) {
        Pageable paging = SortPageMaker.makePageable(pageNumber, pageSize, sortDirection, sortField);
        log.debug("Getting sorted page-list of all Coupons");
        List<Coupon> filteredCouponList = couponRepository
                .findAll(preparePredicateForFindingAllCoupons(couponFilter), paging)
                .toList();
        log.debug("Successfully sorted page-list of all Coupons is got");
        return couponMapper.toCouponDTOList(filteredCouponList);
    }

    @Override
    public CouponDTO findCouponById(UUID id) {
        log.debug("Finding Coupon by ID");
        Optional<Coupon> couponOptional = couponRepository.findById(id)
                .orElseThrow(() -> NotFoundException(String.format("Coupon with id %s not found", id)));
        log.debug("Successfully Coupon is found by ID");
        return couponMapper.toCouponDTO(couponOptional.get());
    }

    @Transactional
    @Override
        public CouponDTO assignCouponToUser(CreateCouponDTO createCouponDTO) {
            log.debug("Finding of certain user by ID");

            User user = userRepository
                    .findById(createCouponDTO.getUserId())
                    .orElseThrow(() -> new NotFoundException(String
                            .format("User with id %s not found", createCouponDTO.getUserId())));

            Discount discount = discountRepository
                    .findById(createCouponDTO.getDiscountId())
                    .orElseThrow(() -> new NotFoundException(String
                            .format("Discount with id %s not found", createCouponDTO.getDiscountId())));
            log.debug("Successfully certain User and Discount are found by ID. Starting coupon creation/saving.");

            Coupon coupon = new Coupon();
            coupon.setUser(user);
            coupon.setDiscount(discount);
            coupon.setDate(LocalDateTime.now());

            Coupon couponSaved = couponRepository.save(coupon);
            log.debug("Successfully new coupon is saved to certain user");
            return couponMapper.toCouponDTO(couponSaved);
        }

    private Predicate preparePredicateForFindingAllCoupons(CouponFilter couponfilter) {
        return ExpressionUtils.and(
                QueryPredicateBuilder.init()
                        .append(couponfilter.getCountryIds(), QCoupon.coupon.discount.vendorLocations.any()
                                .city.country.id::in)
                        .append(couponfilter.getCityIds(), QCoupon.coupon.discount.vendorLocations.any()
                                .city.id::in)
                        .buildOr(),
                QueryPredicateBuilder.init()
                        .append(couponfilter.getUserId(), QCoupon.coupon.user.id::eq)
                        .append(couponfilter.getCreationTimeFrom(), QCoupon.coupon.date::goe)
                        .append(couponfilter.getCreationTimeTo(), QCoupon.coupon.date::loe)
                        .append(couponfilter.getArchived(), QCoupon.coupon.discount.archived::eq)
                        .append(couponfilter.getEndDateFrom(), QCoupon.coupon.discount.endTime::goe)
                        .append(couponfilter.getEndDateTo(), QCoupon.coupon.discount.endTime::loe)
                        .append(couponfilter.getCategoryIds(), QCoupon.coupon.discount.category.id::in)
                        .append(couponfilter.getTagIds(), QCoupon.coupon.discount.tags.any().id::in)
                        .append(couponfilter.getVendorIds(), QCoupon.coupon.discount.vendor.id::in)
                        .buildAnd());
    }
}
