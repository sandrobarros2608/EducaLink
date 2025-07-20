package com.company.educalink.controller;

import com.company.educalink.entity.Teacher;
import com.company.educalink.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller in charge of managing teachers. Allows you to create, consult and delete teachers.
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Tag(name = "Teachers", description = "Endpoints for managing teachers")
@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    /**
     * Exposing the service methods through dependency injection for use in other layers of the application.
     */
    @Autowired
    private TeacherService teacherService;

    /**
     * Create teacher. The teacher can be created without any problems.
     *
     * @param teacher
     * @return Response 201 returning the created object.
     */
    @Operation(summary = "Create teacher.", description = "The teacher can be created without any problems.")
    @PostMapping
    public ResponseEntity<Teacher> saveTeacher(@Valid @RequestBody Teacher teacher) {
        Teacher savedTeacher = teacherService.saveTeacher(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacher);
    }

    /**
     * Get teacher by id. You can obtain a specific teacher.
     *
     * @param id
     * @return Response 200 returning the object.
     */
    @Operation(summary = "Get teacher by id.", description = "You can obtain a specific teacher.")
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> findById(@PathVariable Long id) {
        Teacher teacherId = teacherService.findById(id);
        return ResponseEntity.ok(teacherId);
    }

    /**
     * Get list of teachers. Gets all teachers without pagination.
     *
     * @return Response 200 returning a list of teachers.
     */
    @Operation(summary = "Get list of teachers.", description = "Gets all teachers without pagination.")
    @GetMapping
    public ResponseEntity<List<Teacher>> getAll() {
        List<Teacher> teacherList = teacherService.getAll();
        return ResponseEntity.ok(teacherList);
    }

    /**
     * Update teacher. The teachers can be updated without any problem.
     *
     * @param id
     * @param teacher
     * @return Response 200 returning the updated teacher.
     */
    @Operation(summary = "Update teacher.", description = "The teachers can be updated without any problem.")
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@Valid @PathVariable Long id, @RequestBody Teacher teacher) {
        Teacher updatedTeacher = teacherService.updateTeacher(id, teacher);
        return ResponseEntity.ok(updatedTeacher);
    }

    /**
     * Delete teacher. You can delete teacher without any problem.
     *
     * @param id
     * @return Response 204 returning nothing.
     */
    @Operation(summary = "Delete teacher.", description = "You can delete teacher without any problem.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        teacherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
