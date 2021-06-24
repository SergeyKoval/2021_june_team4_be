package com.exadel.discount.dto;

import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.entity.City;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.UUID;

public class CountryDTO {
    @Null(groups = Create.class)
    private UUID id;
    @NotBlank(groups = Create.class)
    private String name;
    @Null(groups = Create.class)
    private List<City> cities;
}
