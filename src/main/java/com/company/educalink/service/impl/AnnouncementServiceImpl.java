package com.company.educalink.service.impl;

import com.company.educalink.entity.Announcement;
import com.company.educalink.entity.Teacher;
import com.company.educalink.exception.custom.ResourceNotFoundException;
import com.company.educalink.repository.AnnouncementRepository;
import com.company.educalink.repository.TeacherRepository;
import com.company.educalink.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service implementation for managing {@link Announcement} entities.
 * <p>
 * Handles CRUD operations and manages associations with {@link Teacher}.
 * </p>
 *
 * Implements the generic {@link GenericService} interface.
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Service
public class AnnouncementServiceImpl implements GenericService<Announcement, Long> {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    /**
     * Saves a new announcement after verifying the existence of the associated teacher.
     *
     * @param announcement the announcement to be saved
     * @return the saved announcement
     * @throws ResourceNotFoundException if the teacher is not found
     */
    @Override
    public Announcement save(Announcement announcement) {
        Teacher teacher = teacherRepository.findById(announcement.getTeacher().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Teacher.class, announcement.getTeacher().getId()));

        announcement.setTeacher(teacher);
        return announcementRepository.save(announcement);
    }

    /**
     * Finds an announcement by its ID.
     *
     * @param id the ID of the announcement
     * @return the found announcement
     * @throws ResourceNotFoundException if the announcement is not found
     */
    @Transactional(readOnly = true)
    @Override
    public Announcement findById(Long id) {
        return announcementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Announcement.class, id));
    }

    /**
     * Retrieves all announcements from the database.
     *
     * @return a list of all announcements
     */
    @Transactional(readOnly = true)
    @Override
    public List<Announcement> getAll() {
        return announcementRepository.findAll();
    }

    /**
     * Retrieves a paginated list of announcements.
     *
     * @param pageable the pagination information, including page number, size, and sorting
     * @return a {@link Page} containing the announcements for the specified page
     */
    @Override
    public Page<Announcement> getAllPaginated(Pageable pageable) {
        return null;
    }

    /**
     * Updates an existing announcement by its ID.
     * <p>
     * Also verifies and updates the associated teacher.
     * </p>
     *
     * @param id the ID of the announcement to update
     * @param announcement the updated announcement data
     * @return the updated announcement
     * @throws ResourceNotFoundException if the announcement or teacher is not found
     */
    @Override
    public Announcement update(Long id, Announcement announcement) {
        Announcement existingAnnouncement = announcementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Announcement.class, id));

        existingAnnouncement.setTitle(announcement.getTitle());
        existingAnnouncement.setMessage(announcement.getMessage());
        Teacher teacher = teacherRepository.findById(announcement.getTeacher().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Teacher.class, announcement.getTeacher().getId()));

        existingAnnouncement.setTeacher(teacher);
        return announcementRepository.save(existingAnnouncement);
    }

    /**
     * Deletes an announcement by its ID.
     *
     * @param id the ID of the announcement to delete
     * @throws ResourceNotFoundException if the announcement is not found
     */
    @Override
    public void deleteById(Long id) {
        Announcement existingAnnouncement = announcementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Announcement.class, id));
        announcementRepository.delete(existingAnnouncement);
    }
}
