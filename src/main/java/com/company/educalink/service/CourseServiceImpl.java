package com.company.educalink.service;

import com.company.educalink.entity.Course;
import com.company.educalink.exception.custom.CourseNotFoundException;
import com.company.educalink.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        existingCourse.setName(course.getName());
        existingCourse.setDescription(course.getDescription());
        if (course.getLimitStudents() != null && course.getLimitStudents() > 4) {
            existingCourse.setLimitStudents(course.getLimitStudents());
        }
        return courseRepository.save(existingCourse);
    }

    @Override
    public void deleteById(Long id) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        courseRepository.delete(existingCourse);
    }
}
