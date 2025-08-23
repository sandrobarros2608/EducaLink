package com.company.educalink.service;

/**
 * Service interface responsible for sending HTML emails.
 * <p>
 * Provides a contract for sending emails with HTML content to a specific recipient.
 * </p>
 *
 * Implementations of this interface should handle the formatting and delivery
 * of the email message, including exception handling and any required configuration.
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
public interface EmailService {

    /**
     * Sends an HTML-formatted email to the specified recipient.
     *
     * @param to          the recipient's email address
     * @param subject     the subject line of the email
     * @param htmlContent the HTML content of the email body
     */
    void sendWelcomeEmail(String to, String subject, String htmlContent);

    void sendEmailAssignmentTeacher(String to, String subject, String htmlContent);

    void buildEmailSendRegister(Object entity);
}
