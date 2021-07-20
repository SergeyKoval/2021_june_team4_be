package com.exadel.discount.model.dto.discount;

import com.exadel.discount.model.entity.DiscountType;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class UpdateDiscountDTO {
    private UUID categoryId;
    private String name;
    private String description;
    @Size(max = 50, message = "Discount promo should be shorted than {max}")
    private String promo;
    private DiscountType discountType;
    @Min(value = 1, message = "Discount value should be more than {value}")
    private BigDecimal value;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean active;
    private UUID vendorId;
    private Set<UUID> tagIds = new HashSet<>();
    private Set<UUID> vendorLocationsIds = new HashSet<>();
}