package com.company.educalink.service;

import com.company.educalink.entity.Course;

import java.util.List;

public interface CourseService {

    Course saveCourse(Course course);
    Course findById(Long id);
    List<Course> getAll();
    Course updateCourse(Long id, Course course);
    void deleteById(Long id);
}
