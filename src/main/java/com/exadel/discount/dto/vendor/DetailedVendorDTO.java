package com.exadel.discount.dto.vendor;

import com.exadel.discount.dto.location.LocationInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailedVendorDTO {
    private long id;
    private String name;
    private List<LocationInfoDTO> locations;
    private String description;
}
