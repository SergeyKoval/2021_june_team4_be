package com.exadel.discount.dto;

import com.exadel.discount.entity.User;
import com.exadel.discount.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class PlainUserDto {

    private UUID id;
    private String firstName;
    private String secondName;
    private String telephone;
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

    public static PlainUserDto from(User user) {
        PlainUserDto plainUserDto = new PlainUserDto();
        plainUserDto.setId(user.getId());
        plainUserDto.setFirstName(user.getFirstName());
        plainUserDto.setSecondName(user.getLastName());
        plainUserDto.setTelephone(user.getPhone());
        plainUserDto.setEmail(user.getEmail());
        plainUserDto.setLogin(user.getLogin());
        plainUserDto.setPassword(user.getPassword());
        plainUserDto.setRole(user.getRole());
        return plainUserDto;
    }
}
