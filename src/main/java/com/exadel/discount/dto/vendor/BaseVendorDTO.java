package com.exadel.discount.dto.vendor;

import lombok.Data;

import java.util.UUID;

@Data
public class BaseVendorDTO {
    private UUID id;
    private String name;
    private String description;
    private String contacts;
}
