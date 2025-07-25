package com.company.educalink.controller;

import com.company.educalink.entity.Announcement;
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
 * REST controller for managing {@link Announcement} resources.
 * <p>
 * Provides endpoints to create, retrieve, update, and delete announcements associated with teachers.
 * </p>
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Tag(name = "Announcements", description = "Endpoints for managing announcements")
@RestController
@RequestMapping("/api/announcement")
public class AnnouncementController {

    /**
     * Injected service to handle announcement operations.
     */
    @Autowired
    private GenericService<Announcement, Long> genericService;

    /**
     * Creates a new announcement.
     * <p>
     * A valid teacher must exist before an announcement can be created.
     * </p>
     *
     * @param announcement the announcement to create
     * @return HTTP 201 with the created announcement
     */
    @Operation(summary = "Create a new announcement", description = "Creates an announcement associated with an existing teacher.")
    @PostMapping
    public ResponseEntity<Announcement> saveAnnouncement(@Valid @RequestBody Announcement announcement) {
        Announcement savedAnnouncement = genericService.save(announcement);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnnouncement);
    }

    /**
     * Retrieves an announcement by its ID.
     *
     * @param id the ID of the announcement
     * @return HTTP 200 with the found announcement
     */
    @Operation(summary = "Retrieve an announcement by ID", description = "Fetches a specific announcement by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Announcement> findById(@PathVariable Long id) {
        Announcement announcementId = genericService.findById(id);
        return ResponseEntity.ok(announcementId);
    }

    /**
     * Retrieves all announcements.
     *
     * @return HTTP 200 with a list of all announcements
     */
    @Operation(summary = "Retrieve all announcements", description = "Fetches all announcements without pagination.")
    @GetMapping
    public ResponseEntity<List<Announcement>> getAll() {
        List<Announcement> announcementList = genericService.getAll();
        return ResponseEntity.ok(announcementList);
    }

    /**
     * Updates an existing announcement.
     *
     * @param id the ID of the announcement to update
     * @param announcement the updated announcement data
     * @return HTTP 200 with the updated announcement
     */
    @Operation(summary = "Update an existing announcement", description = "Updates a specific announcement by its ID.")
    @PutMapping("/{id}")
    public ResponseEntity<Announcement> updateAnnouncement(@PathVariable Long id, @Valid @RequestBody Announcement announcement) {
        Announcement updatedAnnouncement = genericService.update(id, announcement);
        return ResponseEntity.ok(updatedAnnouncement);
    }

    /**
     * Deletes an announcement by its ID.
     *
     * @param id the ID of the announcement to delete
     * @return HTTP 204 No Content
     */
    @Operation(summary = "Delete an announcement", description = "Deletes a specific announcement by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        genericService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
