package com.company.educalink.exception.custom;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(Long id) {
        super("Teacher with ID: " + id + " not found.");
    }

    public TeacherNotFoundException(String message) {
        super(message);
    }
}
