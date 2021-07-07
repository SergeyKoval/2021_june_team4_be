package com.exadel.discount.dto.city;

import com.exadel.discount.dto.validation.Create;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.UUID;

@Data
public class CityDTO {
    @Null(groups = Create.class, message = "City id should be null")
    private UUID id;
    @NotBlank(groups = Create.class, message = "City name should be not blank")
    private String name;
    @NotNull(groups = Create.class, message = "Country id should be not null")
    private UUID countryId;
    @Null(groups = Create.class, message = "Country name should be null")
    private String countryName;
}
