package com.exadel.discount.dto;

import com.exadel.discount.dto.validation.Create;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.UUID;

@Data
public class VendorLocationDTO {
    @Null(groups = Create.class)
    private UUID id;
    private UUID countryId;
    private UUID cityId;
    private String contact;
    private String coordinates;
}
