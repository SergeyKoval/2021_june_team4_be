package com.exadel.discount.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * This interface describes behaviour for classes that want to generate JWTs.
 */

public interface JwtGenerationService {

    /**
     * This method constructs an access JWT based on a user data.
     *
     * @param userDetails a user data under which the method will generate a JWT.
     * @return a constructed access JWT.
     */
    String generateAccessToken(UserDetails userDetails);

    /**
     * This method constructs a refresh JWT based on a user data.
     *
     * @param userDetails a user data under which the method will generate a JWT.
     * @return a constructed refresh JWT.
     */
    String generateRefreshToken(UserDetails userDetails);
}
