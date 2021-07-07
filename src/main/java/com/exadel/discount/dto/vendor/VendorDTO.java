package com.exadel.discount.dto.vendor;

import com.exadel.discount.dto.location.LocationDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(exclude = {"vendorLocations"})
@ToString(exclude = {"vendorLocations"})
public class VendorDTO {
    private UUID id;
    private String name;
    private String description;
    private String contacts;
    private boolean archived;
    private Set<LocationDTO> vendorLocations;
}
