package com.exadel.discount.dto;

import com.exadel.discount.entity.Person;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class PersonDto {
    private UUID id;
    private String firstName;
    private String secondName;
    private String phone;
    private String email;
    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    private Person.Role role;
    private List<CouponDto> ordersDto = new ArrayList<>();

    public static PersonDto from(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setFirstName(person.getFirstName());
        personDto.setSecondName(person.getSecondName());
        personDto.setPhone(person.getPhone());
        personDto.setEmail(person.getEmail());
        personDto.setLogin(person.getLogin());
        personDto.setPassword(person.getPassword());
        personDto.setRole(person.getRole());
        personDto.setOrdersDto(person.getCoupons().stream().map(CouponDto::from).collect(Collectors.toList()));
        return personDto;
    }
}
