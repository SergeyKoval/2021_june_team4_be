package com.exadel.discount.dto;

import com.exadel.discount.dto.validation.Create;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class TagDTO {
    @Null(groups = Create.class, message = "Tag id should be null")
    private UUID id;
    @NotBlank(groups = Create.class, message = "Tag name should be not blank")
    @Size(max = 50, groups = Create.class, message = "Tag name should be shorter than {max}")
    private String name;
}
