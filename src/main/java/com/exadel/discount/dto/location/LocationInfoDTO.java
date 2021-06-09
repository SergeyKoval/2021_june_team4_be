package com.exadel.discount.dto.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationInfoDTO {
    @Min(value = 0)
    private long id;
    @NotBlank
    private String country;
    @NotBlank
    private String city;
    @NotBlank
    private String address;
    private String contact;
}
