package com.exadel.discount.dto;

import com.exadel.discount.dto.validation.Create;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class DiscountDTO {
    @Null(groups = Create.class)
    private UUID id;
    @NotNull(groups = Create.class, message = "Discount category should be not null")
    private CategoryDTO category;
    @NotBlank(groups = Create.class, message = "Discount name should be not blank")
    private String name;
    private String description;
    @NotBlank(groups = Create.class, message = "Discount promo should be not blank")
    @Size(max = 50, groups = Create.class, message = "Discount promo should be shorted than {max}")
    private String promo;
    @NotNull(groups = Create.class)
    private int percent;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean active;

}
