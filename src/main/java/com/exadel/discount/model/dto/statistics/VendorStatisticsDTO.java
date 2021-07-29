package com.exadel.discount.model.dto.statistics;

import lombok.Data;

import java.util.UUID;

@Data
public class VendorStatisticsDTO extends ItemDTO {
    private Long discountsNumber;

    public VendorStatisticsDTO(UUID id, String name, Long discountsNumber, Long viewNumber,
                               Long numberOfGettingPromo) {
        super(id, name, viewNumber, numberOfGettingPromo);
        this.discountsNumber = discountsNumber;
    }
}
