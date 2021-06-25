package com.exadel.discount.dto.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class CreateLocationDTO {
    @NotBlank
    private String country;
    @NotBlank
    private String city;
    @NotBlank
    private String address;
    private String contact;
}
