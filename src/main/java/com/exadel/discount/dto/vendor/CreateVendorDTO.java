package com.exadel.discount.dto.vendor;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateVendorDTO {
    @NotBlank
    @Size(max = 50)
    private String name;
    private String description;
}
