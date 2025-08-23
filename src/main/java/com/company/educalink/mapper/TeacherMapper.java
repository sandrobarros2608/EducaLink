package com.company.educalink.mapper;

import com.company.educalink.entity.Teacher;
import com.company.educalink.entity.dto.CourseBasicDTO;
import com.company.educalink.entity.dto.TeacherBasicDTO;
import com.company.educalink.entity.dto.TeacherDto;

import java.util.Set;
import java.util.stream.Collectors;

public class TeacherMapper {

    public static TeacherDto toDTO(Teacher teacher){
        if(teacher == null) return null;

        TeacherDto dto = new TeacherDto();
        dto.setId(teacher.getId());
        dto.setName(teacher.getName());
        dto.setLastName(teacher.getLastName());
        dto.setEmail(teacher.getEmail());
        dto.setPhoneNumber(teacher.getPhoneNumber());

        Set<CourseBasicDTO> courseDTOs = teacher.getCourses()
                .stream()
                .map(course -> {
                    CourseBasicDTO c = new CourseBasicDTO();
                    c.setId(course.getId());
                    c.setName(course.getName());
                    return c;
                }).collect(Collectors.toSet());

        dto.setCourses(courseDTOs);

        return dto;
    }

    public static TeacherBasicDTO toBasicDTO(Teacher teacher) {
        if(teacher == null) return null;

        TeacherBasicDTO dto = new TeacherBasicDTO();
        dto.setId(teacher.getId());
        dto.setName(teacher.getName());
        dto.setLastName(teacher.getLastName());
        return dto;
    }
}
