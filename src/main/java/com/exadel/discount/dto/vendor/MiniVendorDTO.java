package com.exadel.discount.dto.vendor;

import com.exadel.discount.dto.validation.Create;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class MiniVendorDTO {
    @NotNull(groups = Create.class)
    private UUID id;

}
