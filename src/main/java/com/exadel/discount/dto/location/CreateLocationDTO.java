package com.exadel.discount.dto.location;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CreateLocationDTO {
    //TODO
    /*private CountryDTO country;
    private CityDTO city;*/
    private String contact;
    private Double coordinateX;
    private Double coordinateY;
    @NotNull(message = "Vendor ID should be not null")
    private UUID vendorId;
}
