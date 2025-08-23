package com.company.educalink.exception.custom;

/**
 * Exception thrown when an entity type is not supported for registration-related emails.
 * <p>
 * This is typically used to indicate that the system does not allow sending
 * registration emails for the specified entity type.
 * </p>
 *
 * Example: {@code throw new UnsupportedRegistrationEntityException(Teacher.class);}
 *
 * Will result in: "Entity type Teacher is not supported for registration emails."
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
public class UnsupportedRegistrationEntityException extends RuntimeException{

    /**
     * Constructs a new UnsupportedRegistrationEntityException with a message
     * indicating that the specified entity type cannot be used for registration emails.
     *
     * @param resourceClass the class of the entity that is not supported for registration emails
     */
    public UnsupportedRegistrationEntityException(Class<?> resourceClass) {
        super("Entity type " + resourceClass.getSimpleName() + " is not supported for registration emails.");
    }
}
