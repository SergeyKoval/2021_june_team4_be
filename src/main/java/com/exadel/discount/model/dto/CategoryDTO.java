package com.exadel.discount.model.dto;

import com.exadel.discount.model.dto.validation.Create;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.UUID;

@Data
public class CategoryDTO {
    @Null(groups = Create.class, message = "Сategory ID should be blank")
    private UUID id;
    @NotBlank(groups = Create.class, message = "Category name should be not blank")
    private String name;
}
