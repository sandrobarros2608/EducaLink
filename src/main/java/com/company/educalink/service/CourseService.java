package com.company.educalink.service;

import com.company.educalink.entity.Course;
import com.company.educalink.entity.dto.CourseDto;

import java.util.List;

public interface CourseService extends GenericService<Course, Long> {

    Course assignTeacher(Long courseId, Long teacherId);

    Course assignStudent(Long courseId, Long studentId);

    List<CourseDto> getAllWithRelations();
}
