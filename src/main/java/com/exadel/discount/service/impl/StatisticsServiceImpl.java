package com.exadel.discount.service.impl;

import com.exadel.discount.model.dto.statistics.CategoryStatisticsDTO;
import com.exadel.discount.model.dto.statistics.DiscountStatisticsDTO;
import com.exadel.discount.model.dto.statistics.StatisticsDTO;
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
    public StatisticsDTO getCategoriesStatistics(String sortBy, String sortDir,
                                                 Integer page, Integer size, StatisticsFilter filter) {
        Pageable pageable = SortPageUtil.makePageable(page, size, sortDir, sortBy);
        List<CategoryStatisticsDTO> categoryStatisticsDTOS;
        if (filter.getCityIds() != null || filter.getCountryIds() != null) {
            categoryStatisticsDTOS = categoryRepository
                    .getCategoriesStatistics(filter.getDateFrom(), filter.getDateTo(),
                            filter.getCityIds(), filter.getCountryIds(), pageable);
        } else {
            categoryStatisticsDTOS = categoryRepository
                    .getCategoriesStatistics(filter.getDateFrom(), filter.getDateTo(), pageable);
        }
        return new StatisticsDTO(categoryStatisticsDTOS);
    }

    @Override
    public StatisticsDTO getDiscountsStatistics(String sortBy, String sortDir, Integer page, Integer size,
                                                StatisticsFilter filter) {
        Pageable pageable = SortPageUtil.makePageable(page, size, sortDir, sortBy);
        List<DiscountStatisticsDTO> discountStatisticsDTOS;
        if (filter.getCityIds() != null || filter.getCountryIds() != null) {
            discountStatisticsDTOS = discountRepository
                    .getDiscountsStatistics(filter.getDateFrom(), filter.getDateTo(),
                            filter.getCityIds(), filter.getCountryIds(), pageable);
        } else {
            discountStatisticsDTOS = discountRepository
                    .getDiscountsStatistics(filter.getDateFrom(), filter.getDateTo(), pageable);
        }
        return new StatisticsDTO(discountStatisticsDTOS);
    }

    @Override
    public StatisticsDTO getVendorsStatistics(String sortBy, String sortDir, Integer page, Integer size,
                                              StatisticsFilter filter) {
        Pageable pageable = SortPageUtil.makePageable(page, size, sortDir, sortBy);
        List<VendorStatisticsDTO> vendorStatisticsDTOS;
        if (filter.getCityIds() != null || filter.getCountryIds() != null) {
            vendorStatisticsDTOS = vendorRepository
                    .getVendorsStatistics(filter.getDateFrom(), filter.getDateTo(),
                            filter.getCityIds(), filter.getCountryIds(), pageable);
        } else {
            vendorStatisticsDTOS = vendorRepository
                    .getVendorsStatistics(filter.getDateFrom(), filter.getDateTo(), pageable);
        }
        return new StatisticsDTO(vendorStatisticsDTOS);
    }
}
