package com.company.educalink.controller;

import com.company.educalink.entity.Course;
import com.company.educalink.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller in charge of managing Courses. Allows you to create, consult and delete courses associated with teachers.
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Tag(name = "Courses", description = "Endpoints for managing courses")
@RestController
@RequestMapping("/api/course")
public class    CourseController {

    /**
     * Exposing the service methods through dependency injection for use in other layers of the application.
     */
    @Autowired
    private CourseService courseService;

    /**
     * Create course. Before a course can be created, a teacher must exist.
     *
     * @param course
     * @return Response 201 returning the created object.
     */
    @Operation(summary = "Create course.", description = "Before a course can be created, a teacher must exist.")
    @PostMapping
    public ResponseEntity<Course> saveCourse(@Valid @RequestBody Course course) {
        Course savedCourse = courseService.saveCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }

    /**
     * Get course by id. You can obtain a specific course.
     *
     * @return Response 200 you can obtain a specific course.
     */
    @Operation(summary = "Get course by id.", description = "You can obtain a specific course.")
    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id) {
        Course courseId = courseService.findById(id);
        return ResponseEntity.ok(courseId);
    }

    /**
     * Get list of courses. Gets all courses without pagination.
     *
     * @return Response 200 returning a list of course.
     */
    @Operation(summary = "Get list of courses.", description = "Gets all courses without pagination.")
    @GetMapping
    public ResponseEntity<List<Course>> getAll() {
        List<Course> courseList = courseService.getAll();
        return ResponseEntity.ok(courseList);
    }

    /**
     * Update course. The courses can be updated without any problem.
     *
     * @param id
     * @param course
     * @return Response 200 returning the updated course.
     */
    @Operation(summary = "Update course.", description = "The courses can be updated without any problem.")
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @Valid @RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(id, course);
        return ResponseEntity.ok(updatedCourse);
    }

    /**
     * Delete course. You can delete course without any problem.
     *
     * @param id
     * @return Response 204 returning nothing.
     */
    @Operation(summary = "Delete course.", description = "You can delete course without any problem.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
