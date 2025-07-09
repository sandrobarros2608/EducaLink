package com.company.educalink.controller;

import com.company.educalink.entity.Publication;
import com.company.educalink.service.PublicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publication")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @PostMapping
    public ResponseEntity<Publication> savePublication(@Valid @RequestBody Publication publication) {
        Publication savedPublication = publicationService.savePublication(publication);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPublication);
    }

    @GetMapping
    public ResponseEntity<List<Publication>> getAll() {
        List<Publication> publicationList = publicationService.getAll();
        return ResponseEntity.ok(publicationList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publication> updatePublication(@PathVariable Long id, @Valid @RequestBody Publication publication) {
        Publication updatedPublication = publicationService.updatePublication(id, publication);
        return ResponseEntity.ok(updatedPublication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        publicationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
