package com.company.educalink.service.impl;

import com.company.educalink.constant.EmailConstants;
import com.company.educalink.entity.Course;
import com.company.educalink.entity.Student;
import com.company.educalink.entity.Teacher;
import com.company.educalink.exception.custom.ResourceNotFoundException;
import com.company.educalink.repository.CourseRepository;
import com.company.educalink.repository.StudentRepository;
import com.company.educalink.repository.TeacherRepository;
import com.company.educalink.service.CourseService;
import com.company.educalink.service.EmailService;
import com.company.educalink.service.GenericService;
import com.company.educalink.util.email.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Service implementation for managing {@link Course} entities.
 * <p>
 * This class handles the business logic for courses, including CRUD operations and the
 * association of a course with a specific {@link Teacher}.
 * </p>
 *
 * Implements the generic {@link GenericService} interface for standardized behavior.
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmailService emailService;

    /**
     * Creates a new course and assigns it to an existing teacher.
     *
     * @param course the course to be saved
     * @return the saved course
     * @throws ResourceNotFoundException if the associated teacher does not exist
     */
    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course assignTeacher(Long courseId, Long teacherId) {
        Course existingCourse = findById(courseId);
        Teacher existingTeacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException(Teacher.class, teacherId));

        existingCourse.getTeachers().add(existingTeacher);
        return courseRepository.save(existingCourse);
    }

    @Override
    public Course assignStudent(Long courseId, Long studentId) {
        Course existingCourse = findById(courseId);
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(Student.class, studentId));

        existingCourse.getStudents().add(existingStudent);
        return courseRepository.save(existingCourse);
    }

    /**
     * Finds a course by its ID.
     *
     * @param id the ID of the course
     * @return the found course
     * @throws ResourceNotFoundException if the course is not found
     */
    @Transactional(readOnly = true)
    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Course.class, id));
    }

    /**
     * Retrieves all courses from the database.
     *
     * @return a list of all courses
     */
    @Transactional(readOnly = true)
    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Page<Course> getAllPaginated(Pageable pageable) {
        return null;
    }

    /**
     * Updates an existing course by its ID.
     * <p>
     * Ensures that the student limit is greater than 4 before updating it.
     * Reassigns the course to an existing teacher.
     * </p>
     *
     * @param id the ID of the course to update
     * @param course the new course data
     * @return the updated course
     * @throws ResourceNotFoundException if the course or teacher is not found
     */
    @Override
    public Course update(Long id, Course course) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Course.class, id));

        existingCourse.setName(course.getName());
        existingCourse.setDescription(course.getDescription());
        if (course.getLimitStudents() != null && course.getLimitStudents() > 4) {
            existingCourse.setLimitStudents(course.getLimitStudents());
        }
        return courseRepository.save(existingCourse);
    }

    /**
     * Deletes a course by its ID.
     *
     * @param id the ID of the course to delete
     * @throws ResourceNotFoundException if the course is not found
     */
    @Override
    public void deleteById(Long id) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Course.class, id));
        courseRepository.delete(existingCourse);
    }

    public void sendEmailAssignmentTeacher(Teacher teacher) {
        Map<String, String> placeholders = EmailUtil.assignmentPlaceholdersBuild(teacher);

        String htmlTemplate = EmailUtil.loadTemplate(EmailConstants.EMAIL_TEMPLATE_ASSIGNMENT_COURSE_PATH);

        String formattedText = EmailUtil.formatTemplate(
                htmlTemplate,
                placeholders
        );

        emailService.sendEmailAssignmentTeacher(teacher.getEmail(), EmailConstants.EMAIL_REGISTRATION_SUBJECT, formattedText);
    }
}
