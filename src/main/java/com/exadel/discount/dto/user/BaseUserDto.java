package com.exadel.discount.dto.user;

import com.exadel.discount.entity.Role;
import com.exadel.discount.entity.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class BaseUserDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String login;
    private String password;
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
