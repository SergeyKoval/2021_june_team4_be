package com.exadel.discount.dto.authentication;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RefreshResponse {
    private final String accessToken;
}
