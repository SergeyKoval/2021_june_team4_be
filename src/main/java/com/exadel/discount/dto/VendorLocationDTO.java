package com.exadel.discount.dto;

import com.exadel.discount.dto.validation.Create;
import lombok.Data;

import javax.validation.constraints.Null;
import java.util.UUID;

@Data
public class VendorLocationDTO {
    @Null(groups = Create.class)
    private UUID id;
    //TODO 
    /*private CountryDTO country;
    private CityDTO city;*/
    private String contact;
    private String coordinates;
}
