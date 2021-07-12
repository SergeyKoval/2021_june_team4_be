package com.exadel.discount.model.dto.location;

import com.exadel.discount.model.dto.city.CityDTO;
import lombok.Data;

import java.util.UUID;

@Data
public class LocationDTO {
    private UUID id;
    private Double latitude;
    private Double longitude;
    private CityDTO city;
}
