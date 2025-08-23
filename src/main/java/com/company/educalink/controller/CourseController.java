package com.company.educalink.controller;

import com.company.educalink.entity.Course;
import com.company.educalink.entity.dto.CourseDto;
import com.company.educalink.mapper.CourseMapper;
import com.company.educalink.service.CourseService;
import com.company.educalink.service.GenericService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing {@link Course} resources.
 * <p>
 * Provides endpoints to create, retrieve, update, and delete courses associated with teachers.
 * </p>
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Tag(name = "Courses", description = "Endpoints for managing courses")
@RestController
@RequestMapping("/api/course")
public class    CourseController {

    /**
     * Injected service to handle course operations.
     */
    @Autowired
    private CourseService courseService;

    /**
     * Creates a new course.
     * <p>
     * A valid teacher must exist before a course can be created.
     * </p>
     *
     * @param course the course to create
     * @return HTTP 201 with the created course
     */
    @Operation(summary = "Create a new course", description = "Creates a new course associated with an existing teacher.")
    @PostMapping
    public ResponseEntity<Course> saveCourse(@Valid @RequestBody Course course) {
        Course savedCourse = courseService.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }

    /**
     * Retrieves a course by its ID.
     *
     * @param id the ID of the course
     * @return HTTP 200 with the found course
     */
    @Operation(summary = "Retrieve a course by ID", description = "Fetches a specific course by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id) {
        Course courseId = courseService.findById(id);
        return ResponseEntity.ok(courseId);
    }

    /**
     * Retrieves all courses.
     *
     * @return HTTP 200 with a list of all courses
     */
    @Operation(summary = "Retrieve all courses", description = "Fetches all courses without pagination.")
    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCoursesWithRelations() {
        List<CourseDto> dtos = courseService.getAllWithRelations();
        return ResponseEntity.ok(dtos);
    }


//
//    @GetMapping
//    public ResponseEntity<List<Course>> getAll() {
//        List<Course> courseList = courseService.getAll();
//        return ResponseEntity.ok(courseList);
//    }

    /**
     * Updates an existing course.
     *
     * @param id the ID of the course to update
     * @param course the updated course data
     * @return HTTP 200 with the updated course
     */
    @Operation(summary = "Update a course", description = "Updates a specific course by its ID.")
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @Valid @RequestBody Course course) {
        Course updatedCourse = courseService.update(id, course);
        return ResponseEntity.ok(updatedCourse);
    }

    @PutMapping("/{courseId}/teacher/{teacherId}")
    public ResponseEntity<Course> assignTeacher(@PathVariable Long courseId, @PathVariable Long teacherId) {
        Course savedCourseTeacher = courseService.assignTeacher(courseId, teacherId);
        return ResponseEntity.ok(savedCourseTeacher);
    }

    @PutMapping("/{courseId}/student/{studentId}")
    public ResponseEntity<Course> assignStudent(@PathVariable Long courseId, @PathVariable Long studentId) {
        Course savedCourseStudent = courseService.assignStudent(courseId, studentId);
        return ResponseEntity.ok(savedCourseStudent);
    }

    /**
     * Deletes a course by its ID.
     *
     * @param id the ID of the course to delete
     * @return HTTP 204 No Content
     */
    @Operation(summary = "Delete a course", description = "Deletes a specific course by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
