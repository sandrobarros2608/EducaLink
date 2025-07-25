package com.company.educalink.service.impl;

import com.company.educalink.constant.EmailConstants;
import com.company.educalink.entity.Teacher;
import com.company.educalink.exception.custom.DuplicateEmailException;
import com.company.educalink.exception.custom.ResourceNotFoundException;
import com.company.educalink.repository.TeacherRepository;
import com.company.educalink.service.EmailService;
import com.company.educalink.service.GenericService;
import com.company.educalink.util.EmailTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service implementation for managing {@link Teacher} entities.
 * <p>
 * This class provides CRUD operations for teachers and handles email
 * notifications upon registration.
 * </p>
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Service
public class TeacherServiceImpl implements GenericService<Teacher, Long> {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private EmailService emailService;

    /**
     * Saves a new {@link Teacher} entity after validating email uniqueness and
     * sends a welcome email.
     *
     * @param teacher the teacher entity to save
     * @return the saved teacher
     * @throws DuplicateEmailException if the email is already in use
     */
    @Override
    public Teacher save(Teacher teacher) {
        Optional<Teacher> teacherEmail = teacherRepository.findByEmail(teacher.getEmail());
        if (teacherEmail.isPresent()) {
            throw new DuplicateEmailException();
        }

        sendWelcomeEmail(teacher);
        return teacherRepository.save(teacher);
    }

    /**
     * Finds a {@link Teacher} by its ID.
     *
     * @param id the ID of the teacher
     * @return the found teacher
     * @throws ResourceNotFoundException if no teacher with the given ID is found
     */
    @Transactional(readOnly = true)
    @Override
    public Teacher findById(Long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Teacher.class, id));
    }

    /**
     * Retrieves all {@link Teacher} entities.
     *
     * @return a list of all teachers
     */
    @Transactional(readOnly = true)
    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    /**
     * Updates an existing {@link Teacher} entity.
     *
     * @param id      the ID of the teacher to update
     * @param teacher the updated teacher data
     * @return the updated teacher
     * @throws ResourceNotFoundException if no teacher with the given ID is found
     * @throws DuplicateEmailException   if the new email is already in use
     */
    @Override
    public Teacher update(Long id, Teacher teacher) {
        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Teacher.class, id));

        existingTeacher.setName(teacher.getName());
        existingTeacher.setLastName(teacher.getLastName());
        if (existingTeacher.getEmail().equals(teacher.getEmail())) {
            throw new DuplicateEmailException();
        }
        existingTeacher.setEmail(teacher.getEmail());
        existingTeacher.setPassword(teacher.getPassword());
        existingTeacher.setPhoneNumber(teacher.getPhoneNumber());
        return teacherRepository.save(existingTeacher);
    }

    /**
     * Deletes a {@link Teacher} by its ID.
     *
     * @param id the ID of the teacher to delete
     * @throws ResourceNotFoundException if no teacher with the given ID is found
     */
    @Override
    public void deleteById(Long id) {
        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Teacher.class, id));
        teacherRepository.delete(existingTeacher);
    }

    /**
     * Sends a welcome email to the registered {@link Teacher}.
     *
     * @param teacher the teacher to whom the email is sent
     */
    public void sendWelcomeEmail(Teacher teacher) {
        Map<String, String> placeholders = EmailTemplateUtil.buildPlaceholders(teacher);

        String htmlTemplate = EmailTemplateUtil.loadTemplate("templates/email/EmailRegistrationText.html");

        String formattedText = EmailTemplateUtil.formatRegisterName(
                htmlTemplate,
                placeholders
        );

        emailService.sendHtmlMessage(teacher.getEmail(), EmailConstants.EMAIL_REGISTRATION_SUBJECT, formattedText);
    }
}
