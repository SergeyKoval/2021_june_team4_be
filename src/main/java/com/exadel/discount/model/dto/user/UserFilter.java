package com.exadel.discount.model.dto.user;

import com.exadel.discount.model.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserFilter {
        private String firstName;
        private String lastName;
        private Role role;
        private List<UUID> cityIds;
        private List<UUID> countryIds;

}
