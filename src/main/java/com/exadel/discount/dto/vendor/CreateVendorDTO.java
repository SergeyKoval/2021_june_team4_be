package com.exadel.discount.dto.vendor;

import com.exadel.discount.dto.location.LocationDTO;
import com.exadel.discount.dto.validation.Create;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Data
public class CreateVendorDTO {
    @Null(groups = Create.class)
    private UUID id;
    @NotBlank(groups = Create.class, message = "Vendor name should be not blank")
    @Size(max = 50, groups = Create.class, message = "Vendor name should be shorted than {max}")
    private String name;
    private String description;

}
