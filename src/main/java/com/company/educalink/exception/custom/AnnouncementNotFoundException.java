package com.company.educalink.exception.custom;

public class AnnouncementNotFoundException extends RuntimeException {

    public AnnouncementNotFoundException(Long id) {
        super("Announcement with ID: " + id + " not found.");
    }

    public AnnouncementNotFoundException(String message) {
        super(message);
    }
}
