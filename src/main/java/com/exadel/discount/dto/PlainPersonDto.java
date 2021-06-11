package com.exadel.discount.dto;

import com.exadel.discount.entity.Person;
import com.exadel.discount.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class PlainPersonDto {

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
    private Role role;

    public static PlainPersonDto from(Person person) {
        PlainPersonDto plainPersonDto = new PlainPersonDto();
        plainPersonDto.setId(person.getId());
        plainPersonDto.setFirstName(person.getFirstName());
        plainPersonDto.setSecondName(person.getSecondName());
        plainPersonDto.setTelephone(person.getPhone());
        plainPersonDto.setEmail(person.getEmail());
        plainPersonDto.setLogin(person.getLogin());
        plainPersonDto.setPassword(person.getPassword());
        plainPersonDto.setRole(person.getRole());
        return plainPersonDto;
    }
}
