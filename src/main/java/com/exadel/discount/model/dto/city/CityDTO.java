package com.exadel.discount.model.dto.city;

import lombok.Data;

import java.util.UUID;

@Data
public class CityDTO {
    private UUID id;
    private String name;
    private UUID countryId;
    private String countryName;
}
