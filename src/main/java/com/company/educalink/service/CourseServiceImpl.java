package com.company.educalink.service;

import com.company.educalink.entity.Course;
import com.company.educalink.entity.Teacher;
import com.company.educalink.exception.custom.CourseNotFoundException;
import com.company.educalink.exception.custom.TeacherNotFoundException;
import com.company.educalink.repository.CourseRepository;
import com.company.educalink.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Course saveCourse(Course course) {
        // Obtain the teacher
        Teacher teacher = teacherRepository.findById(course.getTeacher().getId())
                .orElseThrow(() -> new TeacherNotFoundException(course.getTeacher().getId()));

        course.setTeacher(teacher);
        return courseRepository.save(course);
    }

    @Transactional(readOnly = true)
    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
    }

    @Transactional(readOnly = true)
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

        // Obtain the teacher
        Teacher teacher = teacherRepository.findById(course.getTeacher().getId())
                .orElseThrow(() -> new TeacherNotFoundException(course.getTeacher().getId()));
        existingCourse.setTeacher(teacher);
        return courseRepository.save(existingCourse);
    }

    @Override
    public void deleteById(Long id) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        courseRepository.delete(existingCourse);
    }
}
