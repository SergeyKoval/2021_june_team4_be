package com.exadel.discount.dto.location;

import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.dto.vendor.MiniVendorDTO;
import com.exadel.discount.entity.Vendor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.UUID;

@Data
@Validated
public class CreateLocationDTO {
    //TODO
    /*private CountryDTO country;
    private CityDTO city;*/
    private String contact;
    private String coordinates;
    @NotNull(groups = Create.class)
    private UUID vendorId;
}
