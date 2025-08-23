package com.company.educalink.exception.custom;

public class InvalidPlaceholderEntityException extends RuntimeException{
    public InvalidPlaceholderEntityException(Class<?> resourceClass) {
        super("The entity " + resourceClass.getSimpleName() + " does not support placeholders.");
    }
}
