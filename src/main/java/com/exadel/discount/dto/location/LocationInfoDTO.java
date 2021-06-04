package com.exadel.discount.dto.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationInfoDTO {
    private UUID id;
    private String country;
    private String city;
    private String address;
    private String contact;
}
