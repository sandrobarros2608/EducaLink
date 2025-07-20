package com.company.educalink.controller;

import com.company.educalink.entity.Publication;
import com.company.educalink.service.PublicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller in charge of managing publications. Allows you to create, consult and delete publications associated with teachers.
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Tag(name = "Publications", description = "Endpoints for managing publications")
@RestController
@RequestMapping("/api/publication")
public class PublicationController {

    /**
     * Exposing the service methods through dependency injection for use in other layers of the application.
     */
    @Autowired
    private PublicationService publicationService;

    /**
     * Create publication. Before a publication can be created, a teacher must exist.
     *
     * @param publication
     * @return Response 201 returning the created object.
     */
    @Operation(summary = "Create publication.", description = "Before a publication can be created, a teacher must exist.")
    @PostMapping
    public ResponseEntity<Publication> savePublication(@Valid @RequestBody Publication publication) {
        Publication savedPublication = publicationService.savePublication(publication);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPublication);
    }

    /**
     * Get publication by id. You can obtain a specific publication.
     *
     * @param id
     * @return Response 200 returning the object.
     */
    @Operation(summary = "Get publication by id.", description = "You can obtain a specific publication.")
    @GetMapping("/{id}")
    public ResponseEntity<Publication> findById(@PathVariable Long id) {
        Publication publicationId = publicationService.findById(id);
        return ResponseEntity.ok(publicationId);
    }

    /**
     * Get list of publications. Gets all publications without pagination.
     *
     * @return Response 200 returning a list of publication
     */
    @Operation(summary = "Get list of publications.", description = "Gets all publications without pagination.")
    @GetMapping
    public ResponseEntity<List<Publication>> getAll() {
        List<Publication> publicationList = publicationService.getAll();
        return ResponseEntity.ok(publicationList);
    }

    /**
     * Update publication. The publications can be updated without any problem.
     *
     * @param id
     * @param publication
     * @return Response 200 returning the updated publication.
     */
    @Operation(summary = "Update publication.", description = "The publications can be updated without any problem.")
    @PutMapping("/{id}")
    public ResponseEntity<Publication> updatePublication(@PathVariable Long id, @Valid @RequestBody Publication publication) {
        Publication updatedPublication = publicationService.updatePublication(id, publication);
        return ResponseEntity.ok(updatedPublication);
    }

    /**
     * Delete publication. You can delete publication without any problem.
     *
     * @param id
     * @return Response 204 returning nothing.
     */
    @Operation(summary = "Delete publication.", description = "You can delete publication without any problem.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        publicationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
