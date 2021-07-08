package com.exadel.discount.dto.coupon;

import com.exadel.discount.dto.discount.DiscountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponDTO {
    @NotNull(message = "Coupon ID should be not null")
    private UUID id;
    @NotNull(message = "Coupon date should be not null")
    private LocalDateTime date;
    @NotNull(message = "Discount should be not null")
    private DiscountDTO discountDto;
}