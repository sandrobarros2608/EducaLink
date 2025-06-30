package com.company.educalink.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The field Course Name is required")
    private String name;

    @NotBlank(message = "The field Description is required")
    private String description;

    @Min(value = 5, message = "The limit of students must be at least 5")
    private Integer limitStudents = 5;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = true)
    private Teacher teacher;
}
