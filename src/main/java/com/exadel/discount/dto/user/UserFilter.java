package com.exadel.discount.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserFilter {
        private String firstName;
        private String lastName;
        private String roleFilter;
        private String cityName;
        private String countryName;
}
