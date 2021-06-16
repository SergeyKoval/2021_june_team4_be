package com.exadel.discount.dto;

import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.entity.Category;
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

    @NotNull(groups = Create.class)
    private Category category;

    @NotBlank(groups = Create.class)
    private String name;
    @Null(groups = Create.class)
    private String description;
    @NotBlank(groups = Create.class)
    @Size(max = 50, groups = Create.class)
    private String promo;
    @NotNull(groups = Create.class)
    private int percent;
    @Null(groups = Create.class)
    private LocalDateTime startTime;
    @NotNull(groups = Create.class)
    private LocalDateTime endTime;
    @NotNull(groups = Create.class)
    private boolean active;

}
