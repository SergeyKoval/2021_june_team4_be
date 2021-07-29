package com.exadel.discount.model.dto.statistics;

import lombok.Data;

import java.util.UUID;

@Data
public class DiscountStatisticsDTO extends ItemDTO {

    public DiscountStatisticsDTO(UUID id, String name, Integer viewNumber, Long numberOfGettingPromo) {
        super(id, name, viewNumber, numberOfGettingPromo);
    }
}
