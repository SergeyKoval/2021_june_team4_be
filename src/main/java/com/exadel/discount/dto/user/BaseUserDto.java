package com.exadel.discount.dto.user;

import com.exadel.discount.entity.User;
import com.exadel.discount.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class BaseUserDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String phone;
    @NotNull
    private String email;
    @NotNull
    private String login;
    @JsonIgnore
    @NotNull
    private String password;
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    public static BaseUserDto from(User user) {
        BaseUserDto baseUserDto = new BaseUserDto();
        baseUserDto.setId(user.getId());
        baseUserDto.setFirstName(user.getFirstName());
        baseUserDto.setLastName(user.getLastName());
        baseUserDto.setPhone(user.getPhone());
        baseUserDto.setEmail(user.getEmail());
        baseUserDto.setLogin(user.getLogin());
        baseUserDto.setPassword(user.getPassword());
        baseUserDto.setRole(user.getRole());
        return baseUserDto;
    }
}
