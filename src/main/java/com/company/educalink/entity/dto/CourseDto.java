package com.company.educalink.entity.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CourseDto {
    private Long id;
    private String name;
    private String description;
    private Integer limitStudents;
    private Set<TeacherBasicDTO> teachers;
    private Set<StudentBasicDTO> students;
}
