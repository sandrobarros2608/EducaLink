package com.company.educalink.exception.custom;

/**
 * Exception thrown when an attempt is made to register or save an entity with an email that is already in use.
 * <p>
 * This exception is typically used in service layers to enforce unique email constraints
 * and should be handled appropriately to provide meaningful feedback to the client.
 * </p>
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
public class DuplicateEmailException extends RuntimeException{

    /**
     * Constructs a new DuplicateEmailException with a default message indicating
     * that the email is already in use.
     */
    public DuplicateEmailException() {
        super("Email is already in use.");
    }
}
