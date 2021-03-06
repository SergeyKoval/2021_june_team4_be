package com.exadel.discount.model.dto.city;

import com.exadel.discount.model.dto.validation.Create;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.UUID;

@Data
public class BaseCityDTO {
    @Null(groups = Create.class, message = "City id should be null")
    private UUID id;
    @NotBlank(groups = Create.class, message = "City name should be not blank")
    private String name;
}
