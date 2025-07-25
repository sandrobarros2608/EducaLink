package com.company.educalink.service.impl;

import com.company.educalink.entity.Grade;
import com.company.educalink.exception.custom.ResourceNotFoundException;
import com.company.educalink.repository.GradeRepository;
import com.company.educalink.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service implementation for managing {@link Grade} entities.
 * <p>
 * This service provides standard CRUD operations for grades,
 * such as saving, retrieving, updating and deleting.
 * It ensures validation for the existence of grades when performing
 * update or delete operations.
 * </p>
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Service
public class GradeServiceImpl implements GenericService<Grade, Long> {

    @Autowired
    private GradeRepository gradeRepository;

    /**
     * Saves a new {@link Grade} entity.
     *
     * @param grade the grade to be saved
     * @return the saved grade
     */
    @Override
    public Grade save(Grade grade) {
        return gradeRepository.save(grade);
    }

    /**
     * Retrieves a {@link Grade} by its ID.
     *
     * @param id the ID of the grade to find
     * @return the found grade
     * @throws ResourceNotFoundException if no grade is found with the given ID
     */
    @Transactional(readOnly = true)
    @Override
    public Grade findById(Long id) {
        return gradeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Grade.class, id));
    }

    /**
     * Returns a list of all registered {@link Grade} entities.
     *
     * @return list of grades
     */
    @Transactional(readOnly = true)
    @Override
    public List<Grade> getAll() {
        return gradeRepository.findAll();
    }

    /**
     * Updates the name of an existing {@link Grade}.
     *
     * @param id the ID of the grade to update
     * @param grade the grade data with updated name
     * @return the updated grade
     * @throws ResourceNotFoundException if the grade is not found
     */
    @Override
    public Grade update(Long id, Grade grade) {
        Grade existingGrade = gradeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Grade.class, id));

        existingGrade.setName(grade.getName());
        return gradeRepository.save(existingGrade);
    }

    /**
     * Deletes a {@link Grade} entity by its ID.
     *
     * @param id the ID of the grade to delete
     * @throws ResourceNotFoundException if the grade is not found
     */
    @Override
    public void deleteById(Long id) {
        Grade existingGrade = gradeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Grade.class, id));
        gradeRepository.delete(existingGrade);
    }
}
