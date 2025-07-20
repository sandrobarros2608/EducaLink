package com.company.educalink.controller;

import com.company.educalink.entity.Announcement;
import com.company.educalink.service.AnnouncementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller in charge of managing Announcements. Allows you to create, consult and delete announcements associated with teachers.
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Tag(name = "Announcements", description = "Endpoints for managing announcements")
@RestController
@RequestMapping("/api/announcement")
public class AnnouncementController {

    /**
     * Exposing the service methods through dependency injection for use in other layers of the application.
     */
    @Autowired
    private AnnouncementService announcementService;

    /**
     * Create announcement. Before a announcement can be created, a teacher must exist.
     *
     * @param announcement
     * @return Response 201 returning the created object.
     */
    @Operation(summary = "Create announcement.", description = "Before a announcement can be created, a teacher must exist.")
    @PostMapping
    public ResponseEntity<Announcement> saveAnnouncement(@Valid @RequestBody Announcement announcement) {
        Announcement savedAnnouncement = announcementService.saveAnnouncement(announcement);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnnouncement);
    }

    /**
     * Get announcement by id. You can obtain a specific announcement.
     *
     * @param id
     * @return Response 200 returning the object.
     */
    @Operation(summary = "Get announcement by id.", description = "You can obtain a specific announcement.")
    @GetMapping("/{id}")
    public ResponseEntity<Announcement> findById(@PathVariable Long id) {
        Announcement announcementId = announcementService.findById(id);
        return ResponseEntity.ok(announcementId);
    }

    /**
     * Get list of announcements. Gets all announcement without pagination.
     *
     * @return Response 200 returning a list of announcement.
     */
    @Operation(summary = "Get list of announcement.", description = "Gets all announcement without pagination.")
    @GetMapping
    public ResponseEntity<List<Announcement>> getAll() {
        List<Announcement> announcementList = announcementService.getAll();
        return ResponseEntity.ok(announcementList);
    }

    /**
     * Update announcement. The announcements can be updated without any problem.
     *
     * @param id
     * @param announcement
     * @return Response 200 returning the updated announcement.
     */
    @Operation(summary = "Update announcement.", description = "The announcements can be updated without any problem.")
    @PutMapping("/{id}")
    public ResponseEntity<Announcement> updateAnnouncement(@PathVariable Long id, @Valid @RequestBody Announcement announcement) {
        Announcement updatedAnnouncement = announcementService.updateAnnouncement(id, announcement);
        return ResponseEntity.ok(updatedAnnouncement);
    }

    /**
     * Delete announcement. You can delete announcement without any problem.
     *
     * @param id
     * @return Response 204 returning nothing.
     */
    @Operation(summary = "Delete announcement.", description = "You can delete announcement without any problem.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        announcementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
