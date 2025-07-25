package com.company.educalink.controller;

import com.company.educalink.entity.Publication;
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
 * REST controller for managing Publication entities.
 * <p>
 * Provides endpoints for creating, retrieving, updating, and deleting publications associated with teachers.
 * </p>
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Tag(name = "Publications", description = "Endpoints for managing publications")
@RestController
@RequestMapping("/api/publication")
public class PublicationController {

    /**
     * Generic service for handling publication operations.
     */
    @Autowired
    private GenericService<Publication, Long> genericService;

    /**
     * Creates a new publication.
     * <p>
     * A teacher must exist before creating a publication.
     * </p>
     *
     * @param publication the publication to be created
     * @return HTTP 201 with the created publication
     */
    @Operation(summary = "Create a publication", description = "Creates a new publication. A teacher must exist beforehand.")
    @PostMapping
    public ResponseEntity<Publication> savePublication(@Valid @RequestBody Publication publication) {
        Publication savedPublication = genericService.save(publication);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPublication);
    }

    /**
     * Retrieves a publication by its ID.
     *
     * @param id the ID of the publication
     * @return HTTP 200 with the publication if found
     */
    @Operation(summary = "Get publication by ID", description = "Retrieves a specific publication by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Publication> findById(@PathVariable Long id) {
        Publication publicationId = genericService.findById(id);
        return ResponseEntity.ok(publicationId);
    }

    /**
     * Retrieves all publications.
     *
     * @return HTTP 200 with a list of all publications
     */
    @Operation(summary = "Get all publications", description = "Retrieves a list of all publications without pagination.")
    @GetMapping
    public ResponseEntity<List<Publication>> getAll() {
        List<Publication> publicationList = genericService.getAll();
        return ResponseEntity.ok(publicationList);
    }

    /**
     * Updates an existing publication.
     *
     * @param id the ID of the publication to update
     * @param publication the updated publication data
     * @return HTTP 200 with the updated publication
     */
    @Operation(summary = "Update a publication", description = "Updates the information of an existing publication.")
    @PutMapping("/{id}")
    public ResponseEntity<Publication> updatePublication(@PathVariable Long id, @Valid @RequestBody Publication publication) {
        Publication updatedPublication = genericService.update(id, publication);
        return ResponseEntity.ok(updatedPublication);
    }

    /**
     * Deletes a publication by its ID.
     *
     * @param id the ID of the publication to delete
     * @return HTTP 204 with no content
     */
    @Operation(summary = "Delete a publication", description = "Deletes a publication by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        genericService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
