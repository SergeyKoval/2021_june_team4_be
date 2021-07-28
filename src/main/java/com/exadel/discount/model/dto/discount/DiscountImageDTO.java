package com.exadel.discount.model.dto.discount;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiscountImageDTO {
    private String url;
    private boolean byDefault;
}
