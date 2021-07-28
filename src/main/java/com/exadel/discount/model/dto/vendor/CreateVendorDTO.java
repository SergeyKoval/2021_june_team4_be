package com.exadel.discount.model.dto.vendor;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class CreateVendorDTO {
    @NotBlank(message = "Vendor name should be not null")
    @Size(max = 50, message = "Vendor name should be shorted than {max}")
    private String name;
    private String description;
    private String contacts;
}
