package com.exadel.discount.dto.vendor;

import com.exadel.discount.dto.validation.Create;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateVendorDTO {
    @NotBlank(groups = Create.class, message = "Vendor name should be not blank")
    @Size(max = 50, groups = Create.class, message = "Vendor name should be shorted than {max}")
    private String name;
    private String description;
}
