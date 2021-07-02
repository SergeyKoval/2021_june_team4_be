package com.exadel.discount.dto.discount;

import com.exadel.discount.dto.CategoryDTO;
import com.exadel.discount.dto.TagDTO;
import com.exadel.discount.dto.location.LocationDTO;
import com.exadel.discount.dto.vendor.BaseVendorDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class DiscountDTO {
    private UUID id;
    private CategoryDTO category;
    private String name;
    private String description;
    private String promo;
    private Integer percent;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean active;
    private Set<TagDTO> tags;
    private Set<LocationDTO> vendorLocations;
    private BaseVendorDTO vendor;
}
