package com.exadel.discount.service.impl;

import com.exadel.discount.model.dto.statistics.CategoryStatisticsDTO;
import com.exadel.discount.model.dto.statistics.DiscountStatisticsDTO;
import com.exadel.discount.model.dto.statistics.StatisticsFilter;
import com.exadel.discount.model.dto.statistics.VendorStatisticsDTO;
import com.exadel.discount.repository.CategoryRepository;
import com.exadel.discount.repository.DiscountRepository;
import com.exadel.discount.repository.VendorRepository;
import com.exadel.discount.repository.query.SortPageUtil;
import com.exadel.discount.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsServiceImpl implements StatisticsService {
    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;
    private final DiscountRepository discountRepository;

    @Override
    public List<CategoryStatisticsDTO> getCategoriesStatistics(String sortBy, String sortDir,
                                                               Integer page, Integer size, StatisticsFilter filter) {
        Pageable pageable = SortPageUtil.makePageable(page, size, sortDir, sortBy);
        if (filter.getCityIds() != null || filter.getCountryIds() != null) {
            return categoryRepository
                    .getCategoriesStatistics(filter.getDateFrom(), filter.getDateTo(),
                            filter.getCityIds(), filter.getCountryIds(), pageable);
        } else {
            return categoryRepository
                    .getCategoriesStatistics(filter.getDateFrom(), filter.getDateTo(), pageable);
        }
    }

    @Override
    public List<DiscountStatisticsDTO> getDiscountsStatistics(String sortBy, String sortDir, Integer page, Integer size,
                                                              StatisticsFilter filter) {
        Pageable pageable = SortPageUtil.makePageable(page, size, sortDir, sortBy);
        if (filter.getCityIds() != null || filter.getCountryIds() != null) {
            return discountRepository
                    .getDiscountsStatistics(filter.getDateFrom(), filter.getDateTo(),
                            filter.getCityIds(), filter.getCountryIds(), pageable);
        } else {
            return discountRepository
                    .getDiscountsStatistics(filter.getDateFrom(), filter.getDateTo(), pageable);
        }
    }

    @Override
    public List<VendorStatisticsDTO> getVendorsStatistics(String sortBy, String sortDir, Integer page, Integer size,
                                                          StatisticsFilter filter) {
        Pageable pageable = SortPageUtil.makePageable(page, size, sortDir, sortBy);
        if (filter.getCityIds() != null || filter.getCountryIds() != null) {
            return vendorRepository
                    .getVendorsStatistics(filter.getDateFrom(), filter.getDateTo(),
                            filter.getCityIds(), filter.getCountryIds(), pageable);
        } else {
            return vendorRepository
                    .getVendorsStatistics(filter.getDateFrom(), filter.getDateTo(), pageable);
        }
    }
}
