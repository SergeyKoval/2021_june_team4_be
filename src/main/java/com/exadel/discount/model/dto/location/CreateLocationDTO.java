package com.exadel.discount.model.dto.location;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CreateLocationDTO {
    private Double latitude;
    private Double longitude;
    @NotNull(message = "Vendor ID should be not null")
    private UUID vendorId;
    @NotNull(message = "City ID should be not null")
    private UUID cityId;
}
