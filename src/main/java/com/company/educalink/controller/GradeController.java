package com.company.educalink.controller;

import com.company.educalink.entity.Grade;
import com.company.educalink.service.GradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller in charge of managing Grades. Allows you to create, consult and delete grades.
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Tag(name = "Grades", description = "Endpoints for managing grades")
@RestController
@RequestMapping("/api/grade")
public class GradeController {

    /**
     * Exposing the service methods through dependency injection for use in other layers of the application.
     */
    @Autowired
    private GradeService gradeService;

    /**
     * Create grade. The grade can be created without any problems.
     *
     * @param grade
     * @return Response 201 returning the created object.
     */
    @Operation(summary = "Create grade.", description = "The grade can be created without any problems.")
    @PostMapping
    public ResponseEntity<Grade> saveGrade(@Valid @RequestBody Grade grade) {
        Grade savedGrade = gradeService.saveGrade(grade);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGrade);
    }

    /**
     * Get grade by id. You can obtain a specific grade.
     *
     * @return Response 200 you can obtain a specific grade.
     */
    @Operation(summary = "Get grade by id.", description = "You can obtain a specific grade.")
    @GetMapping("/{id}")
    public ResponseEntity<Grade> findById(@PathVariable Long id) {
        Grade gradeId = gradeService.findById(id);
        return ResponseEntity.ok(gradeId);
    }

    /**
     * Get list of grades. Gets all grades without pagination.
     *
     * @return Response 200 returning a list of grade.
     */
    @Operation(summary = "Get list of grades.", description = "Gets all grades without pagination.")
    @GetMapping
    public ResponseEntity<List<Grade>> getAll() {
        List<Grade> gradeList = gradeService.getAll();
        return ResponseEntity.ok(gradeList);
    }

    /**
     * Update grade. The grades can be updated without any problem.
     *
     * @param id
     * @param grade
     * @return Response 200 returning the updated grade.
     */
    @Operation(summary = "Update grade.", description = "The grades can be updated without any problem.")
    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long id, @Valid @RequestBody Grade grade) {
        Grade updatedGrade = gradeService.updateGrade(id, grade);
        return ResponseEntity.ok(updatedGrade);
    }

    /**
     * Delete grade. You can delete grade without any problem.
     *
     * @param id
     * @return Response 204 returning nothing.
     */
    @Operation(summary = "Delete grade.", description = "You can delete grade without any problem.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        gradeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
