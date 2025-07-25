package com.company.educalink.controller;

import com.company.educalink.entity.Student;
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
 * REST controller for managing Student entities.
 * <p>
 * Provides endpoints for creating, retrieving, updating, and deleting students.
 * </p>
 * A grade must exist before creating a student.
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Tag(name = "Students", description = "Endpoints for managing students")
@RestController
@RequestMapping("/api/student")
public class StudentController {

    /**
     * Generic service used to perform operations on Student entities.
     */
    @Autowired
    private GenericService<Student, Long> genericService;

    /**
     * Creates a new student.
     * <p>
     * A grade must exist before a student can be created.
     * </p>
     *
     * @param student the student to be created
     * @return HTTP 201 with the created student
     */
    @Operation(summary = "Create a student", description = "Creates a new student. A grade must exist beforehand.")
    @PostMapping
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student) {
        Student savedStudent = genericService.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    /**
     * Retrieves a student by ID.
     *
     * @param id the ID of the student
     * @return HTTP 200 with the student if found
     */
    @Operation(summary = "Get student by ID", description = "Retrieves a specific student by their ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) {
        Student studentId = genericService.findById(id);
        return ResponseEntity.ok(studentId);
    }

    /**
     * Retrieves all students.
     *
     * @return HTTP 200 with a list of all students
     */
    @Operation(summary = "Get all students", description = "Retrieves a list of all students without pagination.")
    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        List<Student> studentList = genericService.getAll();
        return ResponseEntity.ok(studentList);
    }

    /**
     * Updates an existing student.
     *
     * @param id the ID of the student to update
     * @param student the updated student data
     * @return HTTP 200 with the updated student
     */
    @Operation(summary = "Update a student", description = "Updates the information of an existing student.")
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@Valid @PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = genericService.update(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    /**
     * Deletes a student by ID.
     *
     * @param id the ID of the student to delete
     * @return HTTP 204 with no content
     */
    @Operation(summary = "Delete a student", description = "Deletes a student by their ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        genericService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
