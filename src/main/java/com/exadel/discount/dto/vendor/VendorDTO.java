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
@EqualsAndHashCode(exclude = {"vendorLocations"})
@ToString(exclude = {"vendorLocations"})
public class VendorDTO {
    private UUID id;
    private String name;
    private String description;
    private Set<LocationDTO> vendorLocations;
}
