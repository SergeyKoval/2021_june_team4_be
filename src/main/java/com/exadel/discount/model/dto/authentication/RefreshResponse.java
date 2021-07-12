package com.exadel.discount.model.dto.authentication;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RefreshResponse {
    private final String accessToken;
}
