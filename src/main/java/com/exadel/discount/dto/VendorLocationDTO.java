package com.exadel.discount.dto;

import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.entity.Vendor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.UUID;

@Data
public class VendorLocationDTO {
    @Null(groups = Create.class)
    private UUID id;
    @NotNull(groups = Create.class)
    private Vendor vendor;
    @Null(groups = Create.class)
    private UUID countryId;
    @Null(groups = Create.class)
    private UUID cityId;
    @Null(groups = Create.class)
    private String contact;
    @Null(groups = Create.class)
    private String coordinates;

}
