package com.exadel.discount.dto.vendor;

import com.exadel.discount.dto.location.LocationInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateVendorDTO {
    @NotBlank
    private String name;
    private List<LocationInfoDTO> locations;
    private String description;
}
