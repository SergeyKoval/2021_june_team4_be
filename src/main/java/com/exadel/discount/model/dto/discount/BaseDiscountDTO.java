package com.exadel.discount.model.dto.discount;

import com.exadel.discount.model.dto.CategoryDTO;
import com.exadel.discount.model.dto.TagDTO;
import com.exadel.discount.model.dto.vendor.BaseVendorDTO;
import com.exadel.discount.model.entity.DiscountType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class BaseDiscountDTO {
    private UUID id;
    private CategoryDTO category;
    private String name;
    private String description;
    private DiscountType discountType;
    private BigDecimal value;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean active;
    private Set<TagDTO> tags;
    private BaseVendorDTO vendor;
}
