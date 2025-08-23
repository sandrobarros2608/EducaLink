package com.company.educalink.service;

import com.company.educalink.entity.Course;
import com.company.educalink.entity.dto.CourseDto;

import java.util.List;

/**
 * Service interface for managing {@link Course} entities.
 * <p>
 * Extends {@link GenericService} to provide standard CRUD operations,
 * and includes additional methods for handling the many-to-many
 * relationships between courses, teachers, and students.
 * </p>
 *
 * @author Sandro
 * @since 1.0.0
 */
public interface CourseService extends GenericService<Course, Long> {

    /**
     * Assigns a teacher to a course.
     * <p>
     * This method establishes or updates the many-to-many relationship
     * between a {@link Course} and a {@link com.company.educalink.entity.Teacher}.
     * </p>
     *
     * @param courseId  the ID of the course to which the teacher will be assigned
     * @param teacherId the ID of the teacher to assign
     * @return the updated {@link Course} with the assigned teacher
     */
    Course assignTeacher(Long courseId, Long teacherId);

    /**
     * Assigns a student to a course.
     * <p>
     * This method establishes or updates the many-to-many relationship
     * between a {@link Course} and a {@link com.company.educalink.entity.Student}.
     * </p>
     *
     * @param courseId  the ID of the course to which the student will be assigned
     * @param studentId the ID of the student to assign
     * @return the updated {@link Course} with the assigned student
     */
    Course assignStudent(Long courseId, Long studentId);

    List<CourseDto> getAllWithRelations();
}
