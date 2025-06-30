package com.company.educalink.exception.custom;

public class PublicationNotFoundException extends RuntimeException {
    public PublicationNotFoundException(Long id) {
        super("Publication with ID: " + id + " not found.");
    }

    public PublicationNotFoundException(String message) {
        super(message);
    }
}
