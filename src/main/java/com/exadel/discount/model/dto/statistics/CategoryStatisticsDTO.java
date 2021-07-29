package com.exadel.discount.model.dto.statistics;

import lombok.Data;

import java.util.UUID;

@Data
public class CategoryStatisticsDTO extends ItemDTO {
    private Long discountsNumber;

    public CategoryStatisticsDTO(UUID id, String name, Long discountsNumber, Long viewNumber,
                                 Long numberOfGettingPromo) {
        super(id, name, viewNumber, numberOfGettingPromo);
        this.discountsNumber = discountsNumber;
    }
}
