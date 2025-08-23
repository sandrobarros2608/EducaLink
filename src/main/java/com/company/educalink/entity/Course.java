package com.company.educalink.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

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

    /* ManyToMany with Teacher. */
    @ManyToMany
    @JoinTable(
            name = "course_teacher",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns =  @JoinColumn(name = "teacher_id")
    )
    /* Temporal Solution */
    @JsonIgnore
    private Set<Teacher> teachers = new HashSet<>();

    /* ManyToMany with Student. */
    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    /* Temporal Solution */
    @JsonIgnore
    private Set<Student> students = new HashSet<>();

}
