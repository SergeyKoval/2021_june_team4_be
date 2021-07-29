package com.exadel.discount.service;

import com.exadel.discount.model.dto.statistics.StatisticsDTO;
import com.exadel.discount.model.dto.statistics.StatisticsFilter;

public interface StatisticsService {

    StatisticsDTO getCategoriesStatistics(String sortBy, String sortDir, Integer page, Integer size,
                                          StatisticsFilter filter);

    StatisticsDTO getDiscountsStatistics(String sortBy, String sortDir, Integer page, Integer size,
                                         StatisticsFilter filter);

    StatisticsDTO getVendorsStatistics(String sortBy, String sortDir, Integer page, Integer size,
                                       StatisticsFilter filter);
}
