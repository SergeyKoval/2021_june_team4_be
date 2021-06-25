package com.exadel.discount.dto;

import com.exadel.discount.dto.validation.Create;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"vendorLocations"})
@ToString(exclude = {"vendorLocations"})
public class VendorDTO {
    @Null(groups = Create.class)
    private UUID id;
    @NotBlank(groups = Create.class, message = "Vendor name should be not blank")
    @Size(max = 50, groups = Create.class, message = "Vendor name should be shorted than {max}")
    private String name;
    private String description;
    @Null(groups = Create.class)
    private List<VendorLocationDTO> vendorLocations;
}
