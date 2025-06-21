package com.company.educalink.exception.custom;

public class GradeNotFoundException extends RuntimeException {
    public GradeNotFoundException(Long id) {
        super("Grade with ID: " + id + " not found.");
    }

    public GradeNotFoundException() {

    }
}
