package com.company.educalink.service.impl;

import com.company.educalink.entity.Publication;
import com.company.educalink.entity.Teacher;
import com.company.educalink.exception.custom.ResourceNotFoundException;
import com.company.educalink.repository.PublicationRepository;
import com.company.educalink.repository.TeacherRepository;
import com.company.educalink.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service implementation for managing {@link Publication} entities.
 * <p>
 * This class provides standard CRUD operations and ensures that each publication
 * is associated with a valid {@link Teacher}.
 *
 * Responsibilities include:
 * <ul>
 *     <li>Validating the existence of a {@link Teacher} before saving or updating</li>
 *     <li>Managing publications through standard CRUD operations</li>
 * </ul>
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Service
public class PublicationServiceImpl implements GenericService<Publication, Long> {

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    /**
     * Saves a new {@link Publication} after validating the associated {@link Teacher}.
     *
     * @param publication the publication to save
     * @return the saved publication
     * @throws ResourceNotFoundException if the provided teacher ID does not exist
     */
    @Override
    public Publication save(Publication publication) {
        Teacher teacher = teacherRepository.findById(publication.getTeacher().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Teacher.class, publication.getTeacher().getId()));

        publication.setTeacher(teacher);
        return publicationRepository.save(publication);
    }

    /**
     * Retrieves a {@link Publication} by its ID.
     *
     * @param id the ID of the publication
     * @return the found publication
     * @throws ResourceNotFoundException if no publication is found with the given ID
     */
    @Transactional(readOnly = true)
    @Override
    public Publication findById(Long id) {
        return publicationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Publication.class, id));
    }

    /**
     * Returns a list of all registered {@link Publication} entities.
     *
     * @return list of publications
     */
    @Transactional(readOnly = true)
    @Override
    public List<Publication> getAll() {
        return publicationRepository.findAll();
    }

    /**
     * Updates an existing {@link Publication} entity with new title, message,
     * and associated {@link Teacher}.
     *
     * @param id the ID of the publication to update
     * @param publication the updated publication information
     * @return the updated publication
     * @throws ResourceNotFoundException if the publication or teacher is not found
     */
    @Override
    public Publication update(Long id, Publication publication) {
        Publication existingPublication = publicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Publication.class, id));

        existingPublication.setTitle(publication.getTitle());
        existingPublication.setMessage(publication.getMessage());
        Teacher teacher = teacherRepository.findById(publication.getTeacher().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Teacher.class, publication.getTeacher().getId()));

        existingPublication.setTeacher(teacher);
        return publicationRepository.save(existingPublication);
    }

    /**
     * Deletes a {@link Publication} entity by its ID.
     *
     * @param id the ID of the publication to delete
     * @throws ResourceNotFoundException if the publication is not found
     */
    @Override
    public void deleteById(Long id) {
        Publication existingPublication = publicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Publication.class, id));
        publicationRepository.delete(existingPublication);
    }
}
