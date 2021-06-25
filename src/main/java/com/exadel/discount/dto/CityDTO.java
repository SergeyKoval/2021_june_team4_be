package com.exadel.discount.dto;

import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.entity.Country;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.UUID;

@Data
public class CityDTO {
    @Null(groups = Create.class)
    private UUID id;

    @NotBlank(groups = Create.class)
    private String name;

    @NotNull(groups = Create.class)
    private Country country;
}
