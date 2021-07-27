package com.exadel.discount.model.dto.statistics;

import lombok.Data;

import java.util.List;

@Data
public class StatisticsDTO {
    private List<? extends ItemDTO> items;
    private int totalCount;

    public StatisticsDTO(List<? extends ItemDTO> items) {
        this.items = items;
        totalCount = items.size();
    }
}
