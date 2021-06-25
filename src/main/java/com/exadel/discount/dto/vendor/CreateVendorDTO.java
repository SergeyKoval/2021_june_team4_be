package com.exadel.discount.dto.vendor;

import com.exadel.discount.dto.location.LocationInfoDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class CreateVendorDTO {
    @NotBlank
    private String name;
    private List<LocationInfoDTO> locations;
    private String description;
}
