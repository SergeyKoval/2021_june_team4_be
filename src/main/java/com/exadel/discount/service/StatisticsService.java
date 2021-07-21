package com.exadel.discount.service;

import com.exadel.discount.model.dto.statistics.CategoryStatisticsDTO;
import com.exadel.discount.model.dto.statistics.DiscountStatisticsDTO;
import com.exadel.discount.model.dto.statistics.StatisticsFilter;
import com.exadel.discount.model.dto.statistics.VendorStatisticsDTO;

import java.util.List;

public interface StatisticsService {

    List<CategoryStatisticsDTO> getCategoriesStatistics(String sortBy, String sortDir, Integer page, Integer size,
                                                        StatisticsFilter filter);

    List<DiscountStatisticsDTO> getDiscountsStatistics(String sortBy, String sortDir, Integer page, Integer size,
                                                       StatisticsFilter filter);

    List<VendorStatisticsDTO> getVendorsStatistics(String sortBy, String sortDir, Integer page, Integer size,
                                                   StatisticsFilter filter);
}
