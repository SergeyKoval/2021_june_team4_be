package com.exadel.discount.model.dto;

import com.exadel.discount.model.dto.city.BaseCityDTO;
import com.exadel.discount.model.dto.validation.Create;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.UUID;

@Data
public class CountryDTO {
    @Null(groups = Create.class, message = "Country id should be null")
    private UUID id;
    @NotBlank(groups = Create.class, message = "Country name should be not blank")
    private String name;
    @Null(groups = Create.class, message = "List of cities should be null")
    private List<BaseCityDTO> cities;
}
