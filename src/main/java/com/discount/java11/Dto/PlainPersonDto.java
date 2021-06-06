package com.discount.java11.Dto;

import com.discount.java11.Entity.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;

@Data
public class PlainPersonDto {

    private Long id;
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
    private String Role;

    public static PlainPersonDto from(Person person) {
        PlainPersonDto plainPersonDto = new PlainPersonDto();
        plainPersonDto.setId(person.getId());
        plainPersonDto.setFirstName(person.getFirstName());
        plainPersonDto.setSecondName(person.getSecondName());
        plainPersonDto.setTelephone(person.getTelephone());
        plainPersonDto.setEmail(person.getEmail());
        plainPersonDto.setLogin(person.getLogin());
        plainPersonDto.setPassword(person.getPassword());
        plainPersonDto.setRole(person.getRole());
        return plainPersonDto;
    }
}
