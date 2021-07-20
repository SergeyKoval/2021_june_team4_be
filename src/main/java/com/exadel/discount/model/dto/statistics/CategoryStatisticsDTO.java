package com.exadel.discount.model.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CategoryStatisticsDTO {
    private UUID id;
    private String name;
    private Long discountsNumber;
    private Long viewNumber;
    private Long numberOfGettingPromo;
}
