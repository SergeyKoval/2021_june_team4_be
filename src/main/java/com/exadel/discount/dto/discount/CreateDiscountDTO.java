package com.exadel.discount.dto.discount;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class CreateDiscountDTO {
    @NotNull(message = "Discount category should be not null")
    private UUID categoryId;
    @NotBlank(message = "Discount name should be not blank")
    private String name;
    private String description;
    @NotBlank(message = "Discount promo should be not blank")
    @Size(max = 50, message = "Discount promo should be shorted than {max}")
    private String promo;
    @Min(value = 1, message = "Discount percent should be more than {min}")
    @Max(value = 100, message = "Discount percent should be more less than {max}")
    private Integer percent;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean active;
    @NotNull(message = "Vendor should be not null")
    private UUID vendorId;
    private Set<UUID> tagIds = new HashSet<>();
    @NotNull(message = "Discount should have at least one location")
    private Set<UUID> vendorLocationsIds = new HashSet<>();
}
