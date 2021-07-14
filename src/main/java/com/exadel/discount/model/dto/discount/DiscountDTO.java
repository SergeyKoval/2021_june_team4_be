package com.exadel.discount.model.dto.discount;

import com.exadel.discount.model.dto.CategoryDTO;
import com.exadel.discount.model.dto.TagDTO;
import com.exadel.discount.model.dto.location.LocationDTO;
import com.exadel.discount.model.dto.vendor.BaseVendorDTO;
import com.exadel.discount.model.entity.DiscountType;
import lombok.Data;

import java.math.BigDecimal;
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
    private DiscountType discountType;
    private BigDecimal value;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean active;
    private boolean archived;
    private Set<TagDTO> tags;
    private Set<LocationDTO> vendorLocations;
    private BaseVendorDTO vendor;
    private boolean favorite;
}
