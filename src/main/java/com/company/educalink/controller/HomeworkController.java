package com.company.educalink.controller;

import com.company.educalink.entity.Homework;
import com.company.educalink.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Homework")
public class HomeworkController {

    @Autowired
    private HomeworkService homeworkService;

    // Crear nueva tarea
    @PostMapping
    public ResponseEntity<Homework> createHomework(@RequestBody Homework homework) {
        Homework saved = homeworkService.saveHomework(homework);
        return ResponseEntity.ok(saved);
    }

    // Obtener tarea por ID
    @GetMapping("/{id}")
    public ResponseEntity<Homework> getHomeworkById(@PathVariable Long id) {
        Homework homework = homeworkService.findById(id);
        return ResponseEntity.ok(homework);
    }

    // Listar todas las tareas
    @GetMapping
    public ResponseEntity<List<Homework>> getAllHomeworks() {
        return ResponseEntity.ok(homeworkService.getAll());
    }

    // Actualizar tarea existente
    @PutMapping("/{id}")
    public ResponseEntity<Homework> updateHomework(@PathVariable Long id, @RequestBody Homework homework) {
        Homework updated = homeworkService.updateHomework(id, homework);
        return ResponseEntity.ok(updated);
    }

    // Eliminar tarea
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHomework(@PathVariable Long id) {
        homeworkService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
