package com.company.educalink.controller;

import com.company.educalink.entity.Student;
import com.company.educalink.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller in charge of managing Students. Allows you to create, consult and delete students.
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Tag(name = "Students", description = "Endpoints for managing Students")
@RestController
@RequestMapping("/api/student")
public class StudentController {

    /**
     * Exposing the service methods through dependency injection for use in other layers of the application.
     */
    @Autowired
    private StudentService studentService;

    /**
     * Create student. Before a student can be created, a grade must exist.
     *
     * @param student
     * @return Response 201 returning the created object.
     */
    @Operation(summary = "Create student.", description = "Before a student can be created, a grade must exist.")
    @PostMapping
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    /**
     * Get student by id. You can obtain a specific student.
     *
     * @param id
     * @return Response 200 returning the object.
     */
    @Operation(summary = "Get student by id.", description = "You can obtain a specific student.")
    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) {
        Student studentId = studentService.findById(id);
        return ResponseEntity.ok(studentId);
    }

    /**
     * Get list of students. Gets all students without pagination.
     *
     * @return Response 200 returning a list of students.
     */
    @Operation(summary = "Get list of students.", description = "Gets all students without pagination.")
    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        List<Student> studentList = studentService.getAll();
        return ResponseEntity.ok(studentList);
    }

    /**
     * Update student. The students can be updated without any problem.
     *
     * @param id
     * @param student
     * @return Response 200 returning the updated student.
     */
    @Operation(summary = "Update student.", description = "The students can be updated without any problem.")
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@Valid @PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    /**
     * Delete student. You can delete student without any problem.
     *
     * @param id
     * @return Response 204 returning nothing.
     */
    @Operation(summary = "Delete student.", description = "You can delete student without any problem.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
