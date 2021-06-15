package com.exadel.discount.dto.tag;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateTagDTO {
    @NotBlank
    @Size(max = 50)
    private String name;
}
