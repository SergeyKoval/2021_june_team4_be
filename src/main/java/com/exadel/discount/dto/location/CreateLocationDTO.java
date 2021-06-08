package com.exadel.discount.dto.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateLocationDTO {
    @NotBlank
    private String country;
    @NotBlank
    private String city;
    @NotBlank
    private String address;
    private String contact;
}
