package com.exadel.discount.model.dto.user;

import com.exadel.discount.model.entity.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserFilter {
        private String firstName;
        private String lastName;
        private Role role;
        private String cityName;
        private String countryName;
}
