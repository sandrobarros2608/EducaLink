package com.company.educalink.controller;

import com.company.educalink.entity.Qualification;
import com.company.educalink.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qualifications")
public class QualificationController {
    @Autowired
    private QualificationService qualificationService;

    // Crear una nueva calificación
    @PostMapping
    public ResponseEntity<Qualification> createQualification(@RequestBody Qualification qualification) {
        Qualification saved = qualificationService.saveQualification(qualification);
        return ResponseEntity.ok(saved);
    }

    // Obtener una calificación por ID
    @GetMapping("/{id}")
    public ResponseEntity<Qualification> getById(@PathVariable Long id) {
        Qualification qualification = qualificationService.findById(id);
        if (qualification != null) {
            return ResponseEntity.ok(qualification);
        }
        return ResponseEntity.notFound().build();
    }

    // Obtener todas las calificaciones
    @GetMapping
    public ResponseEntity<List<Qualification>> getAll() {
        return ResponseEntity.ok(qualificationService.getAll());
    }

    // Actualizar una calificación
    @PutMapping("/{id}")
    public ResponseEntity<Qualification> update(@PathVariable Long id, @RequestBody Qualification qualification) {
        Qualification updated = qualificationService.updateQualification(id, qualification);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar una calificación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        qualificationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
