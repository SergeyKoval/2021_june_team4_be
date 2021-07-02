package com.exadel.discount.dto.location;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CreateLocationDTO {
    private Double coordinateX;
    private Double coordinateY;
    @NotNull(message = "Vendor ID should be not null")
    private UUID vendorId;
}
