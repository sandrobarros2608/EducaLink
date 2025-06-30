package com.company.educalink.controller;

import com.company.educalink.entity.Announcement;
import com.company.educalink.service.AnnouncementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @PostMapping
    public ResponseEntity<Announcement> saveAnnouncement(@Valid @RequestBody Announcement announcement) {
        Announcement savedAnnouncement = announcementService.saveAnnouncement(announcement);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnnouncement);
    }

    @GetMapping
    public ResponseEntity<List<Announcement>> getAll() {
        List<Announcement> announcementList = announcementService.getAll();
        return ResponseEntity.ok(announcementList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Announcement> updateAnnouncement(@PathVariable Long id, @Valid @RequestBody Announcement announcement) {
        Announcement updatedAnnouncement = announcementService.updateAnnouncement(id, announcement);
        return ResponseEntity.ok(updatedAnnouncement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        announcementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
