package com.company.educalink.exception.custom;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Long id) {
        super("Course with ID: " + id + " not found.");
    }

    public CourseNotFoundException(String message) {
        super(message);
    }
}
