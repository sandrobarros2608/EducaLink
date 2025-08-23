package com.company.educalink.service;

/**
 * Service interface responsible for sending HTML-based emails within the EducaLink platform.
 * <p>
 * Provides a contract for sending welcome emails during user registration,
 * assignment notifications, and other email-related operations.
 * </p>
 *
 * Implementations of this interface are expected to:
 * <ul>
 *     <li>Use a mail transport mechanism (e.g., {@code JavaMailSender})</li>
 *     <li>Format templates with placeholders before sending</li>
 *     <li>Handle messaging exceptions gracefully</li>
 * </ul>
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
public interface EmailService {

    /**
     * Sends a welcome email to a newly registered user (e.g., {@link com.company.educalink.entity.Student}
     * or {@link com.company.educalink.entity.Teacher}).
     * <p>
     * The email body should be provided as HTML and may include images
     * (such as the EducaLink logo) using inline resources.
     * </p>
     *
     * @param to          the recipient's email address
     * @param subject     the subject line of the email
     * @param htmlContent the HTML-formatted content of the email body
     * @throws IllegalStateException if the email could not be sent
     */
    void sendWelcomeEmail(String to, String subject, String htmlContent);

    /**
     * Sends an email to notify a teacher of their assignment to a course.
     * <p>
     * This method is intended for handling the many-to-many relationship
     * between courses and teachers, sending the corresponding notification.
     * </p>
     *
     * @param to          the teacher's email address
     * @param subject     the subject line of the email
     * @param htmlContent the HTML-formatted content of the email body
     * @throws IllegalStateException if the email could not be sent
     */
    void sendEmailAssignmentTeacher(String to, String subject, String htmlContent);

    /**
     * Builds and sends a registration welcome email based on the entity type.
     * <p>
     * Supported entities:
     * <ul>
     *     <li>{@link com.company.educalink.entity.Student}</li>
     *     <li>{@link com.company.educalink.entity.Teacher}</li>
     * </ul>
     * For unsupported entity types, an exception should be thrown.
     * </p>
     *
     * @param entity the entity representing the registered user
     * @throws com.company.educalink.exception.custom.UnsupportedRegistrationEntityException
     *         if the entity type is not supported for registration emails
     */
    void buildEmailSendRegister(Object entity);
}
