package com.company.educalink.entity.dto;

import lombok.Data;

import java.util.Set;

@Data
public class TeacherDto {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;


    private Set<CourseBasicDTO> courses;

}
