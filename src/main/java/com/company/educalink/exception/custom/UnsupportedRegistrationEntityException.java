package com.company.educalink.exception.custom;

public class UnsupportedRegistrationEntityException extends RuntimeException{
    public UnsupportedRegistrationEntityException(Class<?> resourceClass) {
        super("Entity type " + resourceClass.getSimpleName() + " is not supported for registration emails.");
    }
}
