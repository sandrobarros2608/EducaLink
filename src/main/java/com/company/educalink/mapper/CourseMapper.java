package com.company.educalink.mapper;

import com.company.educalink.entity.Course;
import com.company.educalink.entity.dto.CourseBasicDTO;
import com.company.educalink.entity.dto.CourseDto;
import com.company.educalink.entity.dto.StudentBasicDTO;
import com.company.educalink.entity.dto.TeacherBasicDTO;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CourseMapper {

    public static CourseDto toDTO(Course course) {
        if (course == null) return null;

        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setDescription(course.getDescription());
        dto.setLimitStudents(course.getLimitStudents());

        // copiar la colección para evitar ConcurrentModificationException
        Set<TeacherBasicDTO> teacherDTOs = new HashSet<>();
        for (var teacher : new HashSet<>(course.getTeachers())) {
            TeacherBasicDTO t = new TeacherBasicDTO();
            t.setId(teacher.getId());
            t.setName(teacher.getName());
            t.setLastName(teacher.getLastName());
            teacherDTOs.add(t);
        }
        dto.setTeachers(teacherDTOs);

        Set<StudentBasicDTO> studentDTOs = course.getStudents()
                .stream()
                .map(student -> {
                    StudentBasicDTO s = new StudentBasicDTO();
                    s.setId(student.getId());
                    s.setName(student.getName());
                    s.setLastName(student.getLastName());
                    return s;
                })
                .collect(Collectors.toSet());

        dto.setStudents(studentDTOs);

        return dto;
    }
}
