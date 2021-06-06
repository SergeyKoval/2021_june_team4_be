package com.discount.java11.Dto;

import com.discount.java11.Entity.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PersonDto {
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
    private List<OrderDto> ordersDto = new ArrayList<>();

    public static PersonDto from(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setFirstName(person.getFirstName());
        personDto.setSecondName(person.getSecondName());
        personDto.setTelephone(person.getTelephone());
        personDto.setEmail(person.getEmail());
        personDto.setLogin(person.getLogin());
        personDto.setPassword(person.getPassword());
        personDto.setRole(person.getRole());
        personDto.setOrdersDto(person.getOrders().stream().map(OrderDto::from).collect(Collectors.toList()));
        return personDto;
    }
}
