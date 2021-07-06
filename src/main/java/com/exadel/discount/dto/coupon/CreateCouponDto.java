package com.exadel.discount.dto.coupon;

import com.exadel.discount.dto.discount.DiscountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCouponDto {

    @NotNull(message = "Coupon ID should be not null")
    private UUID userId;

    @NotNull(message = "Discount ID should be not null")
    private UUID discountId;
}
