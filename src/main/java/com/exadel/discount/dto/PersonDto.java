package com.exadel.discount.dto;

import com.exadel.discount.config.StringToEnumConverter;
import com.exadel.discount.entity.Person;
import com.exadel.discount.entity.Role;
import lombok.Data;

import javax.persistence.Convert;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class PersonDto {
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
    //@Enumerated(EnumType.ORDINAL)
    @Convert(converter = StringToEnumConverter.class)
    private Role role;
    private List<CouponDto> ordersDto = new ArrayList<>();

    public static PersonDto from(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setFirstName(person.getFirstName());
        personDto.setLastName(person.getLastName());
        personDto.setPhone(person.getPhone());
        personDto.setEmail(person.getEmail());
        personDto.setLogin(person.getLogin());
        personDto.setPassword(person.getPassword());
        personDto.setRole(person.getRole());
        personDto.setOrdersDto(person.getCoupons().stream().map(CouponDto::from).collect(Collectors.toList()));
        return personDto;
    }
}
