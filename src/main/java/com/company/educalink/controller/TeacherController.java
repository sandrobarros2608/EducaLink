package com.company.educalink.controller;

import com.company.educalink.entity.Teacher;
import com.company.educalink.service.GenericService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing Teacher entities.
 * <p>
 * Provides endpoints for creating, retrieving, updating, and deleting teachers.
 * </p>
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Tag(name = "Teachers", description = "Endpoints for managing teachers")
@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    /**
     * Generic service used to perform operations on Teacher entities.
     */
    @Autowired
    private GenericService<Teacher, Long> genericService;

    /**
     * Creates a new teacher.
     *
     * @param teacher the teacher to be created
     * @return HTTP 201 with the created teacher
     */
    @Operation(summary = "Create a teacher", description = "Creates a new teacher.")
    @PostMapping
    public ResponseEntity<Teacher> saveTeacher(@Valid @RequestBody Teacher teacher) {
        Teacher savedTeacher = genericService.save(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacher);
    }

    /**
     * Retrieves a teacher by ID.
     *
     * @param id the ID of the teacher
     * @return HTTP 200 with the teacher if found
     */
    @Operation(summary = "Get teacher by ID", description = "Retrieves a specific teacher by their ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> findById(@PathVariable Long id) {
        Teacher teacherId = genericService.findById(id);
        return ResponseEntity.ok(teacherId);
    }

    /**
     * Retrieves all teachers.
     *
     * @return HTTP 200 with a list of all teachers
     */
    @Operation(summary = "Get all teachers", description = "Retrieves a list of all teachers without pagination.")
    @GetMapping
    public ResponseEntity<List<Teacher>> getAll() {
        List<Teacher> teacherList = genericService.getAll();
        return ResponseEntity.ok(teacherList);
    }

    /**
     * Updates an existing teacher.
     *
     * @param id the ID of the teacher to update
     * @param teacher the updated teacher data
     * @return HTTP 200 with the updated teacher
     */
    @Operation(summary = "Update a teacher", description = "Updates the information of an existing teacher.")
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@Valid @PathVariable Long id, @RequestBody Teacher teacher) {
        Teacher updatedTeacher = genericService.update(id, teacher);
        return ResponseEntity.ok(updatedTeacher);
    }

    /**
     * Deletes a teacher by ID.
     *
     * @param id the ID of the teacher to delete
     * @return HTTP 204 with no content
     */
    @Operation(summary = "Delete a teacher", description = "Deletes a teacher by their ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        genericService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Teacher>> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Teacher> teachers = genericService.getAllPaginated(pageable);
        return ResponseEntity.ok(teachers);
    }

}
