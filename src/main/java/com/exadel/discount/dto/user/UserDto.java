package com.exadel.discount.dto.user;

import com.exadel.discount.dto.coupon.CouponDto;
import com.exadel.discount.dto.favorite.FavoriteDto;
import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.entity.City;
import com.exadel.discount.model.security.Role;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotNull
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

    //TODO convert to CityDto
    @NotNull
    private City city;
}
