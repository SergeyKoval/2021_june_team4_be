package com.exadel.discount.dto.vendor;

import com.exadel.discount.dto.location.LocationInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class DetailedVendorDTO {
    @Min(value = 0)
    private long id;
    @NotBlank
    private String name;
    private List<LocationInfoDTO> locations;
    private String description;
}
