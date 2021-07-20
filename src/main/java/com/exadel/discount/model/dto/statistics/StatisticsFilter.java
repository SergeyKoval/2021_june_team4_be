package com.exadel.discount.model.dto.statistics;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class StatisticsFilter {
    private static final String DEFAULT_DATE_FROM = "2000-01-01T00:00:00";
    private static final String DEFAULT_DATE_TO = "3000-01-01T00:00:00";

    private List<UUID> countryIds;
    private List<UUID> cityIds;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;

    public StatisticsFilter build() {
        if (dateFrom == null) {
            dateFrom = LocalDateTime.parse(DEFAULT_DATE_FROM);
        }
        if (dateTo == null) {
            dateTo = LocalDateTime.parse(DEFAULT_DATE_TO);
        }
        return new StatisticsFilter(countryIds, cityIds, dateFrom, dateTo);
    }
}
