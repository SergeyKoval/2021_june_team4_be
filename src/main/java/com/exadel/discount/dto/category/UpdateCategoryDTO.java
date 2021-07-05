package com.exadel.discount.dto.category;

import com.exadel.discount.dto.validation.Create;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UpdateCategoryDTO {
    @NotNull(groups = Create.class, message = "Category ID should be not blank")
    private UUID id;
    @NotBlank(groups = Create.class, message = "Category name should be not blank")
    private String name;
}
