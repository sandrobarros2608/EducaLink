package com.company.educalink.service;

import com.company.educalink.entity.Course;

public interface CourseService extends GenericService<Course, Long> {

    Course assignTeacher(Long courseId, Long teacherId);

    Course assignStudent(Long courseId, Long studentId);
}
