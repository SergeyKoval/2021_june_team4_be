package com.exadel.discount.model.dto.statistics;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class StatisticsFilter {
    private static final LocalDateTime DEFAULT_DATE_FROM = LocalDateTime.parse("2000-01-01T00:00:00");
    private static final LocalDateTime DEFAULT_DATE_TO = LocalDateTime.parse("3000-01-01T00:00:00");

    private List<UUID> countryIds;
    private List<UUID> cityIds;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;

    public StatisticsFilter build() {
        if (dateFrom == null) {
            dateFrom = DEFAULT_DATE_FROM;
        }
        if (dateTo == null) {
            dateTo = DEFAULT_DATE_TO;
        }
        return new StatisticsFilter(countryIds, cityIds, dateFrom, dateTo);
    }
}
