package com.exadel.discount.dto.user;

import com.exadel.discount.dto.coupon.CouponDto;
import com.exadel.discount.dto.favorite.FavoriteDto;
import com.exadel.discount.entity.Role;
import com.exadel.discount.entity.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class UserDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String login;
    private String password;
    private Role role;
    private List<CouponDto> couponDtos = new ArrayList<>();
    private List<FavoriteDto> favoriteDtos = new ArrayList<>();

    public static UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhone(user.getPhone());
        userDto.setEmail(user.getEmail());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setCouponDtos(user.getCoupons().stream().map(CouponDto::from).collect(Collectors.toList()));
        userDto.setFavoriteDtos(user.getFavorites().stream().map(FavoriteDto::from).collect(Collectors.toList()));
        return userDto;
    }

}
