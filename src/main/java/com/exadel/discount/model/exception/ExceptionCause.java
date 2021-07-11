package com.exadel.discount.model.exception;

/**
 * This enumeration represents logical words for the exceptions bodies that
 * are transferred to a client code and are used for localisation.
 * <p>
 * It is used in the class {@link ExceptionDetails}.
 */
public enum ExceptionCause {
    /**
     * It means a problem at the level of authentication.
     * It is intended for an incorrect login or password.
     * But it can be delegated to {@link #INCORRECT_VALUE} if
     * a problem causes at the level of data validation by {@link javax.validation.Valid}.
     */
    AUTHENTICATION_FAILED,
    /**
     * It means a problem at the level of data validation.
     * It is intended for {@link javax.validation.Valid} exceptions
     * or an incorrect incoming format.
     */
    INCORRECT_VALUE,
    /**
     * It means a problem at the level of database data searching.
     * It is intended for a situation where search key founds nothing in database.
     */
    NOT_FOUND_VALUE,
    /**
     * It means a problem at the level of token logic.
     * It is intended for situation where the client contacting the resource server
     * transfers an incorrect access token or
     * contacting the authentication server transfers an incorrect refresh token.
     */
    ACCESS_DENIED,
    /**
     * It means a problem at the level of database data deletion.
     * It is intended for situation where database data cannot be deleted.
     */
    DELETION_DENIED,
    /**
     * It means an unforeseen problem at the level of all the server.
     */
    UNCAUGHT_EXCEPTION
}
