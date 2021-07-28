package com.exadel.discount.model.dto.image;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiscountImageDTO {
    private final String image;
    private boolean byDefault;
}
