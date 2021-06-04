package com.exadel.discount.dto.vendor;

import com.exadel.discount.dto.location.LocationInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateVendorDTO {
    private String name;
    private Set<LocationInfoDTO> locations;
    private String description;
}
