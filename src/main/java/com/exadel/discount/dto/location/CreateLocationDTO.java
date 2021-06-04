package com.exadel.discount.dto.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateLocationDTO {
    private String country;
    private String city;
    private String address;
    private String contact;
}
