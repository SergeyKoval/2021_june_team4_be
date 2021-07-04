package com.exadel.discount.dto.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCouponDto {
    @NotNull
    private UUID userId;
    @NotNull
    private UUID discountId;
}
