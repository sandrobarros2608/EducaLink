package com.company.educalink.exception.custom;

/**
 * Exception thrown when an entity does not support placeholder functionality.
 * <p>
 * This is typically used to signal that a certain entity type cannot be used
 * in contexts where placeholder substitution is expected.
 * </p>
 *
 * Example: {@code throw new InvalidPlaceholderEntityException(Course.class);}
 *
 * Will result in: "The entity Course does not support placeholders."
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
public class InvalidPlaceholderEntityException extends RuntimeException{

    /**
     * Exception thrown when an entity does not support placeholder functionality.
     * <p>
     * This is typically used to signal that a certain entity type cannot be used
     * in contexts where placeholder substitution is expected.
     * </p>
     *
     * Example: {@code throw new InvalidPlaceholderEntityException(Course.class);}
     *
     * Will result in: "The entity Course does not support placeholders."
     *
     * @author Sandro Barros
     * @since 1.0.0
     */
    public InvalidPlaceholderEntityException(Class<?> resourceClass) {
        super("The entity " + resourceClass.getSimpleName() + " does not support placeholders.");
    }
}
