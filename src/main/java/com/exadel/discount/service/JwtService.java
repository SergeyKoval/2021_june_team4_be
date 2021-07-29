package com.exadel.discount.service;

/**
 * This interface describes behaviour for classes that want to provide claims from JWTs.
 */

public interface JwtService {

    /**
     * This method provide subject claim getting from a given JWT.
     *
     * @param token a JWT that will be parsed for the subject claim extraction.
     * @return an extracted subject claim.
     */
    String getSubject(String token);

    /**
     * This method provide role claim getting from a given JWT.
     *
     * @param token a JWT that will be parsed for the role claim extraction.
     * @return an extracted role claim.
     */
    String getRole(String token);
}
