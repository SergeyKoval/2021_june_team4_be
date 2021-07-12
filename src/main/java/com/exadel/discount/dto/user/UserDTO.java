package com.exadel.discount.dto.user;

import com.exadel.discount.dto.city.CityDTO;
import com.exadel.discount.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotNull(message = "User ID should be not null")
    private UUID id;
    @NotNull(message = "User first name should be not null")
    private String firstName;
    @NotNull(message = "User last name should be not null")
    private String lastName;
    @NotNull(message = "User's phone should be not null")
    private String phone;
    @NotNull(message = "User's email should be not null")
    private String email;
    @NotNull(message = "User's role should be not null")
    private Role role;
    @NotBlank(message = "City of User should be not null")
    private CityDTO city;
}
