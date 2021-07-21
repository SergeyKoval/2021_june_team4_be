package com.exadel.discount.service.impl;

import com.exadel.discount.exception.CreationRestrictedException;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.model.dto.coupon.CouponDTO;
import com.exadel.discount.model.dto.coupon.CouponFilter;
import com.exadel.discount.model.dto.mapper.CouponMapper;
import com.exadel.discount.model.entity.Coupon;
import com.exadel.discount.model.entity.Discount;
import com.exadel.discount.model.entity.QCoupon;
import com.exadel.discount.model.entity.User;
import com.exadel.discount.repository.CouponRepository;
import com.exadel.discount.repository.DiscountRepository;
import com.exadel.discount.repository.UserRepository;
import com.exadel.discount.repository.query.QueryPredicateBuilder;
import com.exadel.discount.repository.query.SortPageUtil;
import com.exadel.discount.service.CouponService;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Slf4j
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;
    private final UserRepository userRepository;
    private final DiscountRepository discountRepository;
    private static final int SEARCH_WORD_MIN_LENGTH = 3;

    @Override
    public List<CouponDTO> getAll(int pageNumber, int pageSize, String sortDirection, String sortField,
                                  CouponFilter couponFilter) {
        Sort sort = SortPageUtil.makeSort(sortDirection, sortField);
        Pageable paging = PageRequest.of(pageNumber, pageSize, sort);
        log.debug("Getting sorted page-list of all Coupons");
        List<Coupon> filteredCouponList;
        if (preparePredicateForFindingAllCoupons(couponFilter) == null) {
            filteredCouponList = couponRepository.findAll(paging).toList();
        } else {
            List<UUID> couponIds = couponRepository
                    .findAllCouponIds(preparePredicateForFindingAllCoupons(couponFilter), paging);
            filteredCouponList = couponRepository.findAllByIdIn(couponIds, sort);
        }
        log.debug("Successfully sorted page-list of all Coupons is got");
        return couponMapper.toCouponDTOList(filteredCouponList);
    }

    @Override
    public List<CouponDTO> search(Integer size, String searchText) {
        log.debug("Getting sorted page-list of all Coupons by searchText");
        Sort sort = Sort.by("date").descending();
        List<UUID> couponsIds = couponRepository
                .findAllCouponIds(prepareSearchPredicate(searchText), PageRequest.of(0, size, sort));
        List<Coupon> coupons = couponRepository
                .findAllByIdIn(couponsIds, sort);
        log.debug("Successfully got sorted page-list of all Coupons by searchText");
        return couponMapper.toCouponDTOList(coupons);
    }

    @Override
    public CouponDTO findCouponById(UUID id) {
        log.debug("Finding Coupon by ID");
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Coupon with id %s not found", id)));
        log.debug("Successfully Coupon is found by ID");
        return couponMapper.toCouponDTO(coupon);
    }

    @Transactional
    @Override
    public CouponDTO assignCouponToUser(UUID discountId) {
        log.debug(String.format("Check: if user already have Favorite of Discount with ID %s ", discountId));
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        if (couponRepository.existsByDiscountIdAndUserEmail(discountId, userEmail)) {
            throw new CreationRestrictedException(String
                    .format("Coupon for Discount with id %s does already exist", discountId));
        }
        User user = userRepository
                .findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with email %s not found", userEmail)));

        Discount discount = discountRepository
                .findById(discountId)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Discount with id %s not found", discountId)));
        log.debug("Successfully certain User and Discount are found by ID. Starting Coupon creation/saving.");

        Coupon coupon = new Coupon();
        coupon.setUser(user);
        coupon.setDiscount(discount);
        coupon.setDate(LocalDateTime.now());
        coupon = couponRepository.save(coupon);
        log.debug(String.format("Successfully added Coupon for Discount with ID %s to user", discountId));
        return couponMapper.toCouponDTO(coupon);
    }

    private Predicate preparePredicateForFindingAllCoupons(CouponFilter couponFilter) {
        return ExpressionUtils.and(
                QueryPredicateBuilder.init()
                        .append(couponFilter.getCountryIds(),
                                QCoupon.coupon.discount.vendorLocations.any().city.country.id::in)
                        .append(couponFilter.getCityIds(),
                                QCoupon.coupon.discount.vendorLocations.any().city.id::in)
                        .buildOr(),
                QueryPredicateBuilder.init()
                        .append(couponFilter.getUserId(), QCoupon.coupon.user.id::eq)
                        .append(couponFilter.getCreationTimeFrom(), QCoupon.coupon.date::goe)
                        .append(couponFilter.getCreationTimeTo(), QCoupon.coupon.date::loe)
                        .append(couponFilter.getArchived(), QCoupon.coupon.discount.archived::eq)
                        .append(couponFilter.getEndDateFrom(), QCoupon.coupon.discount.endTime::goe)
                        .append(couponFilter.getEndDateTo(), QCoupon.coupon.discount.endTime::loe)
                        .append(couponFilter.getCategoryIds(), QCoupon.coupon.discount.category.id::in)
                        .append(couponFilter.getTagIds(), QCoupon.coupon.discount.tags.any().id::in)
                        .append(couponFilter.getVendorIds(), QCoupon.coupon.discount.vendor.id::in)
                        .buildAnd());
    }

    private Predicate prepareSearchPredicate(String searchText) {
        List<Predicate> searchPredicates = Stream
                .of(StringUtils.split(searchText, StringUtils.SPACE))
                .filter(word -> word.length() >= SEARCH_WORD_MIN_LENGTH)
                .map(word -> QueryPredicateBuilder.init()
                        .append(word, QCoupon.coupon.discount.name::containsIgnoreCase)
                        .append(word, QCoupon.coupon.discount.description::containsIgnoreCase)
                        .append(word, QCoupon.coupon.discount.vendor.name::containsIgnoreCase)
                        .append(word, QCoupon.coupon.discount.category.name::containsIgnoreCase)
                        .append(word, QCoupon.coupon.discount.tags.any().name::containsIgnoreCase)
                        .buildOr())
                .collect(Collectors.toList());
        return ExpressionUtils.allOf(searchPredicates);
    }
}
