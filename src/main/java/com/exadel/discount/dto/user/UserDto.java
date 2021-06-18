package com.exadel.discount.dto.user;

import com.exadel.discount.dto.coupon.CouponDto;
import com.exadel.discount.dto.favorite.FavoriteDto;
import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.entity.Role;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class UserDto {
    @Null(groups = Create.class, message = "User id should be null")
    private UUID id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String phone;
    @NotNull
    private String email;
    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    private Role role;
    private List<CouponDto> couponDtos = new ArrayList<>();
    private List<FavoriteDto> favoriteDtos = new ArrayList<>();
}
