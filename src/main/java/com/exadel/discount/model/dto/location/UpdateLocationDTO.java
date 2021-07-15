package com.exadel.discount.model.dto.location;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UpdateLocationDTO {
    private Double latitude;
    private Double longitude;
    private UUID vendorId;
    private UUID cityId;
}
