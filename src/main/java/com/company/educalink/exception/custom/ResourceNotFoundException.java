package com.company.educalink.exception.custom;

/**
 * Exception thrown when a requested resource is not found in the system.
 * <p>
 * This is commonly used in service layers to indicate that an entity with a given
 * identifier does not exist in the database.
 * </p>
 *
 * Example: {@code throw new ResourceNotFoundException(User.class, 1L);}
 *
 * Will result in: "User with ID: 1 not found."
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with a message indicating the
     * resource type and the ID that was not found.
     *
     * @param resourceClass the class of the resource (e.g., User.class)
     * @param id the ID of the missing resource
     */
    public ResourceNotFoundException(Class<?> resourceClass, Long id) {
        super(resourceClass.getSimpleName() + " with ID: " + id + " not found.");
    }
}
