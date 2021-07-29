package com.exadel.discount.model.dto.user;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserFilter {
        private String firstName;
        private String lastName;
        private List<UUID> cityIds;
        private List<UUID> countryIds;

}
