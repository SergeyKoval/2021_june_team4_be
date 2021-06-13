package com.exadel.discount.dto.user;

import com.exadel.discount.dto.coupon.CouponDto;
import com.exadel.discount.entity.User;
import com.exadel.discount.entity.Role;
import lombok.Data;

import javax.validation.constraints.NotNull;
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
    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    private Role role;
    private List<CouponDto> couponDtos = new ArrayList<>();

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
        return userDto;
    }

}
