package com.exadel.discount.dto.location;

import lombok.Data;

import java.util.UUID;

@Data
public class LocationDTO {
    private UUID id;
    //TODO 
    /*private CountryDTO country;
    private CityDTO city;*/
    private String contact;
    private Double coordinateX;
    private Double coordinateY;
}
