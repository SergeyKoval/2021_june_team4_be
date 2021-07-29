package com.exadel.discount.model.dto.vendor;

import com.exadel.discount.model.dto.discount.BaseDiscountDTO;
import com.exadel.discount.model.dto.location.LocationDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(exclude = {"vendorLocations", "discounts"})
@ToString(exclude = {"vendorLocations", "discounts"})
public class VendorDTO {
    private UUID id;
    private String name;
    private String description;
    private String contacts;
    private String vendorImage;
    private boolean archived;
    private Set<LocationDTO> vendorLocations;
    private Set<BaseDiscountDTO> discounts;
}
