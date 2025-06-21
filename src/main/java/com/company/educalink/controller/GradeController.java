package com.company.educalink.controller;

import com.company.educalink.entity.Grade;
import com.company.educalink.service.GradeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @PostMapping
    public ResponseEntity<Grade> saveGrade(@Valid @RequestBody Grade grade) {
        Grade savedGrade = gradeService.saveGrade(grade);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGrade);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grade> findById(@PathVariable Long id) {
        Grade gradeId = gradeService.findById(id);
        return ResponseEntity.ok(gradeId);
    }

    @GetMapping
    public ResponseEntity<List<Grade>> getAll() {
        List<Grade> gradeList = gradeService.getAll();
        return ResponseEntity.ok(gradeList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long id, @Valid @RequestBody Grade grade) {
        Grade updatedGrade = gradeService.updateGrade(id, grade);
        return ResponseEntity.ok(updatedGrade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        gradeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
