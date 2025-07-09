package com.company.educalink.controller;

import com.company.educalink.entity.Submission;
import com.company.educalink.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;


    @PostMapping
    public Submission createSubmission(@RequestBody Submission submission) {
        return submissionService.saveSubmission(submission);
    }

    // Obtener una entrega por ID
    @GetMapping("/{id}")
    public Submission getSubmissionById(@PathVariable Long id) {
        return submissionService.findById(id);
    }

    // Obtener todas las entregas
    @GetMapping
    public List<Submission> getAllSubmissions() {
        return submissionService.getAll();
    }

    // Actualizar una entrega existente
    @PutMapping("/{id}")
    public Submission updateSubmission(@PathVariable Long id, @RequestBody Submission submission) {
        return submissionService.updateSubmission(id, submission);
    }

    // Eliminar una entrega
    @DeleteMapping("/{id}")
    public void deleteSubmission(@PathVariable Long id) {
        submissionService.deleteById(id);
    }
}
