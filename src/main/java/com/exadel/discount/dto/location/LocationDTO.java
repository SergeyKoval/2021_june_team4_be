package com.exadel.discount.dto.location;

import lombok.Data;

import java.util.UUID;

@Data
public class LocationDTO {
    private UUID id;
    private String contact;
    private String coordinates;
}
