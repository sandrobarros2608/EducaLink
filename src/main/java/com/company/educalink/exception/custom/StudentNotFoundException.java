package com.company.educalink.exception.custom;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Long id) {
        super("Student with ID: " + id + " not found.");
    }

    public StudentNotFoundException(String message) {
        super(message);
    }
}
