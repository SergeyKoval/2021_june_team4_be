package com.exadel.discount.dto.user;

import com.exadel.discount.entity.Country;
import com.exadel.discount.entity.Role;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserCityDto {
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
    @NotBlank
    private String cityName;
    @NotNull
    private String country;
}