package com.exadel.discount.dto.coupon;

import com.exadel.discount.dto.user.UserDto;
import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.entity.User;
import com.sun.istack.NotNull;
import lombok.Data;


import javax.persistence.JoinColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CouponDto {
    @Null(groups = Create.class, message = "Coupon id should be null")
    private UUID id;
    @NotNull
    private LocalDateTime date;
}
