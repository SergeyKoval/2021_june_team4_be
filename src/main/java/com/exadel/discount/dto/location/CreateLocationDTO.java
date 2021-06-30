package com.exadel.discount.dto.location;

import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.dto.vendor.MiniVendorDTO;
import com.exadel.discount.entity.Vendor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.UUID;

@Data
public class CreateLocationDTO {
    @Null(groups = Create.class)
    private UUID id;
    //TODO
    /*private CountryDTO country;
    private CityDTO city;*/
    private String contact;
    private String coordinates;
    @NotNull(groups = Create.class)
    private MiniVendorDTO vendor;
}
