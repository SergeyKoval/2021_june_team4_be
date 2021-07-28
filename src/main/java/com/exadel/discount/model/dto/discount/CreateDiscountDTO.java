package com.exadel.discount.model.dto.discount;

import com.exadel.discount.model.entity.DiscountType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
    @NotNull(message = "Type of discount  should be not blank")
    private DiscountType discountType;
    @Min(value = 1, message = "Discount value should be more than {min}")
    private BigDecimal value;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean active;
    @NotNull(message = "Vendor should be not null")
    private UUID vendorId;
    @NotNull(message = "One image must be provided at least")
    private List<MultipartFile> givenDiscountImages = new ArrayList<>();
    private Set<UUID> tagIds = new HashSet<>();
    @NotNull(message = "Discount should have at least one location")
    private Set<UUID> vendorLocationsIds = new HashSet<>();
}
