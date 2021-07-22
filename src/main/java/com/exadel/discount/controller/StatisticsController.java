package com.exadel.discount.controller;

import com.exadel.discount.model.dto.statistics.CategoryStatisticsDTO;
import com.exadel.discount.model.dto.statistics.DiscountStatisticsDTO;
import com.exadel.discount.model.dto.statistics.StatisticsFilter;
import com.exadel.discount.model.dto.statistics.VendorStatisticsDTO;
import com.exadel.discount.security.annotation.AdminAccess;
import com.exadel.discount.service.StatisticsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/categories")
    @ApiOperation("Get statistics for Categories")
    @AdminAccess
    public List<CategoryStatisticsDTO> getCategoriesStatistics(@RequestParam(defaultValue = "0", required = false) Integer page,
                                                               @RequestParam(defaultValue = "20", required = false) Integer size,
                                                               @RequestParam(defaultValue = "name", required = false)
                                                                       String sortBy,
                                                               @RequestParam(defaultValue = "asc", required = false)
                                                                       String sortDirection,
                                                               @RequestParam(required = false) List<UUID> countryId,
                                                               @RequestParam(required = false) List<UUID> cityId,
                                                               @RequestParam(required = false)
                                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                       LocalDateTime dateFrom,
                                                               @RequestParam(required = false)
                                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                       LocalDateTime dateTo) {
        StatisticsFilter filter = StatisticsFilter
                .builder()
                .countryIds(countryId)
                .cityIds(cityId)
                .dateFrom(dateFrom)
                .dateTo(dateTo)
                .build();
        return statisticsService.getCategoriesStatistics(sortBy, sortDirection, page, size, filter);
    }

    @GetMapping("/discounts")
    @ApiOperation("Get statistics for Discounts")
    @AdminAccess
    public List<DiscountStatisticsDTO> getDiscountsStatistics(@RequestParam(defaultValue = "0", required = false) Integer page,
                                                              @RequestParam(defaultValue = "20", required = false) Integer size,
                                                              @RequestParam(defaultValue = "name", required = false)
                                                                      String sortBy,
                                                              @RequestParam(defaultValue = "asc", required = false)
                                                                      String sortDirection,
                                                              @RequestParam(required = false) List<UUID> countryId,
                                                              @RequestParam(required = false) List<UUID> cityId,
                                                              @RequestParam(required = false)
                                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                      LocalDateTime dateFrom,
                                                              @RequestParam(required = false)
                                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                      LocalDateTime dateTo) {
        StatisticsFilter filter = StatisticsFilter
                .builder()
                .countryIds(countryId)
                .cityIds(cityId)
                .dateFrom(dateFrom)
                .dateTo(dateTo)
                .build();
        return statisticsService.getDiscountsStatistics(sortBy, sortDirection, page, size, filter);
    }

    @GetMapping("/vendors")
    @ApiOperation("Get statistics for Vendors")
    @AdminAccess
    public List<VendorStatisticsDTO> getVendorsStatistics(@RequestParam(defaultValue = "0", required = false) Integer page,
                                                          @RequestParam(defaultValue = "20", required = false) Integer size,
                                                          @RequestParam(defaultValue = "name", required = false)
                                                                  String sortBy,
                                                          @RequestParam(defaultValue = "asc", required = false)
                                                                  String sortDirection,
                                                          @RequestParam(required = false) List<UUID> countryId,
                                                          @RequestParam(required = false) List<UUID> cityId,
                                                          @RequestParam(required = false)
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                  LocalDateTime dateFrom,
                                                          @RequestParam(required = false)
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                  LocalDateTime dateTo) {
        StatisticsFilter filter = StatisticsFilter
                .builder()
                .countryIds(countryId)
                .cityIds(cityId)
                .dateFrom(dateFrom)
                .dateTo(dateTo)
                .build();
        return statisticsService.getVendorsStatistics(sortBy, sortDirection, page, size, filter);
    }
}
