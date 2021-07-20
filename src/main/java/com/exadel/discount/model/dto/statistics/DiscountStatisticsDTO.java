package com.exadel.discount.model.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DiscountStatisticsDTO {
    private UUID id;
    private String name;
    private Integer viewNumber;
    private Long numberOfGettingPromo;
}
