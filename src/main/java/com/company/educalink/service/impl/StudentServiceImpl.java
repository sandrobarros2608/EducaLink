package com.company.educalink.service.impl;

import com.company.educalink.constant.EmailConstants;
import com.company.educalink.entity.Grade;
import com.company.educalink.entity.Student;
import com.company.educalink.exception.custom.DuplicateEmailException;
import com.company.educalink.exception.custom.ResourceNotFoundException;
import com.company.educalink.repository.GradeRepository;
import com.company.educalink.repository.StudentRepository;
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
 * Service implementation for managing {@link Student} entities.
 * <p>
 * This class provides standard CRUD operations as well as additional logic such as:
 * <ul>
 *   <li>Checking for duplicate emails</li>
 *   <li>Validating and setting the associated {@link Grade}</li>
 *   <li>Sending welcome emails on registration</li>
 * </ul>
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Service
public class StudentServiceImpl implements GenericService<Student, Long> {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private EmailService emailService;

    /**
     * Saves a new {@link Student} entity after validating email uniqueness and
     * assigning the corresponding {@link Grade}. Also sends a welcome email.
     * <p>
     * Assumes prior validation (e.g., via {@code @Valid} in the controller layer).
     *
     * @param student the student entity to save
     * @return the saved student
     * @throws DuplicateEmailException if the email is already registered
     * @throws ResourceNotFoundException if the provided grade ID does not exist
     */
    @Override
    public Student save(Student student) {
        Optional<Student> studentEmail = studentRepository.findByEmail(student.getEmail());
        if (studentEmail.isPresent()) {
            throw new DuplicateEmailException();
        }

        Grade grade = gradeRepository.findById(student.getGrade().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Grade.class, student.getGrade().getId()));

        student.setGrade(grade);

        sendWelcomeEmail(student);

        return studentRepository.save(student);
    }

    /**
     * Retrieves a {@link Student} by its ID.
     *
     * @param id the ID of the student
     * @return the found student
     * @throws ResourceNotFoundException if no student is found with the given ID
     */
    @Transactional(readOnly = true)
    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Student.class, id));
    }

    /**
     * Returns a list of all registered {@link Student} entities.
     *
     * @return list of students
     */
    @Transactional(readOnly = true)
    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    /**
     * Updates an existing {@link Student} entity with new information.
     * <p>
     * Also verifies the existence of the associated {@link Grade}.
     *
     * @param id the ID of the student to update
     * @param student the updated student information
     * @return the updated student
     * @throws ResourceNotFoundException if the student or the grade is not found
     */
    @Override
    public Student update(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Student.class, id));

        existingStudent.setName(student.getName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPassword(student.getPassword());
        existingStudent.setPhoneNumber(student.getPhoneNumber());
        Grade grade = gradeRepository.findById(student.getGrade().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Grade.class, student.getGrade().getId()));

        existingStudent.setGrade(grade);
        return studentRepository.save(existingStudent);
    }

    /**
     * Deletes a {@link Student} entity by its ID.
     *
     * @param id the ID of the student to delete
     * @throws ResourceNotFoundException if the student is not found
     */
    @Override
    public void deleteById(Long id) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Student.class, id));
        studentRepository.delete(existingStudent);
    }

    /**
     * Sends a welcome HTML email to the newly registered {@link Student}.
     * <p>
     * Uses placeholder values within a predefined template to personalize the message.
     *
     * @param student the student to whom the email will be sent
     */
    public void sendWelcomeEmail(Student student) {
        Map<String, String> placeholders = EmailTemplateUtil.buildPlaceholders(student);

        String htmlTemplate = EmailTemplateUtil.loadTemplate("templates/email/EmailRegistrationText.html");

        String formattedText = EmailTemplateUtil.formatRegisterName(
                htmlTemplate,
                placeholders
        );
        emailService.sendHtmlMessage(student.getEmail(), EmailConstants.EMAIL_REGISTRATION_SUBJECT, formattedText);
    }
}
