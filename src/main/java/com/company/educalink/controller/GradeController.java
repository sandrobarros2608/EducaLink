package com.company.educalink.controller;

import com.company.educalink.entity.Grade;
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
 * REST controller for managing Grade entities.
 * <p>
 * Provides endpoints for creating, retrieving, updating, and deleting grades.
 * </p>
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Tag(name = "Grades", description = "Endpoints for managing grades")
@RestController
@RequestMapping("/api/grade")
public class GradeController {

    /**
     * Generic service for handling grade operations.
     */
    @Autowired
    private GenericService<Grade, Long> genericService;

    /**
     * Creates a new grade.
     *
     * @param grade the grade to be created
     * @return HTTP 201 with the created grade
     */
    @Operation(summary = "Create a grade", description = "Creates a new grade entity.")
    @PostMapping
    public ResponseEntity<Grade> saveGrade(@Valid @RequestBody Grade grade) {
        Grade savedGrade = genericService.save(grade);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGrade);
    }

    /**
     * Retrieves a grade by its ID.
     *
     * @param id the ID of the grade
     * @return HTTP 200 with the grade if found
     */
    @Operation(summary = "Get grade by ID", description = "Retrieves a specific grade by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Grade> findById(@PathVariable Long id) {
        Grade gradeId = genericService.findById(id);
        return ResponseEntity.ok(gradeId);
    }

    /**
     * Retrieves all grades.
     *
     * @return HTTP 200 with a list of all grades
     */
    @Operation(summary = "Get all grades", description = "Retrieves a list of all grades.")
    @GetMapping
    public ResponseEntity<List<Grade>> getAll() {
        List<Grade> gradeList = genericService.getAll();
        return ResponseEntity.ok(gradeList);
    }

    /**
     * Updates an existing grade.
     *
     * @param id the ID of the grade to update
     * @param grade the updated grade data
     * @return HTTP 200 with the updated grade
     */
    @Operation(summary = "Update a grade", description = "Updates the information of an existing grade.")
    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long id, @Valid @RequestBody Grade grade) {
        Grade updatedGrade = genericService.update(id, grade);
        return ResponseEntity.ok(updatedGrade);
    }

    /**
     * Deletes a grade by its ID.
     *
     * @param id the ID of the grade to delete
     * @return HTTP 204 with no content
     */
    @Operation(summary = "Delete a grade", description = "Deletes a grade by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        genericService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
