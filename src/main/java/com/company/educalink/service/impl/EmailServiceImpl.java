package com.company.educalink.service.impl;

import com.company.educalink.constant.EmailConstants;
import com.company.educalink.entity.Student;
import com.company.educalink.entity.Teacher;
import com.company.educalink.exception.custom.UnsupportedRegistrationEntityException;
import com.company.educalink.service.EmailService;
import com.company.educalink.util.EmailUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Implementation of the {@link EmailService} interface for sending HTML-formatted emails.
 * <p>
 * This service leverages Spring's {@link JavaMailSender} to create and send
 * emails that support HTML content and inline resources (such as images).
 * </p>
 *
 * <p><b>Responsibilities:</b></p>
 * <ul>
 *   <li>Sending welcome emails to new students and teachers</li>
 *   <li>Sending assignment notification emails to teachers</li>
 *   <li>Building and formatting registration emails based on entity type</li>
 *   <li>Handling and wrapping messaging exceptions</li>
 * </ul>
 *
 * @author
 *   Sandro Barros
 * @since 1.0.0
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Sends a welcome email with HTML content to the specified recipient.
     * <p>
     * This email typically includes the EducaLink logo as an inline image and
     * ensures proper rendering by using UTF-8 encoding.
     * </p>
     *
     * @param to          the recipient's email address
     * @param subject     the subject line of the email
     * @param htmlContent the HTML-formatted body of the email
     * @throws IllegalStateException if the email could not be sent due to a {@link MessagingException}
     */
    @Override
    public void sendWelcomeEmail(String to, String subject, String htmlContent) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(EmailConstants.EMAIL_FROM_TO);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            ClassPathResource image = new ClassPathResource(EmailConstants.EMAIL_LOGO_PATH);
            helper.addInline(EmailConstants.EMAIL_LOGO_CONTENT_ID, image);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new IllegalStateException("No se pudo enviar el correo", e);
        }
    }

    /**
     * Sends an email to notify a teacher of their assignment to a course.
     * <p>
     * The email body should be provided in HTML format and will include
     * the EducaLink logo as an inline resource.
     * </p>
     *
     * @param to          the teacher's email address
     * @param subject     the subject line of the notification
     * @param htmlContent the HTML-formatted body of the email
     * @throws IllegalStateException if the email could not be sent due to a {@link MessagingException}
     */
    @Override
    public void sendEmailAssignmentTeacher(String to, String subject, String htmlContent) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(EmailConstants.EMAIL_FROM_TO);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            ClassPathResource image = new ClassPathResource(EmailConstants.EMAIL_LOGO_PATH);
            helper.addInline(EmailConstants.EMAIL_LOGO_CONTENT_ID, image);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new IllegalStateException("No se pudo enviar el correo", e);
        }
    }

    /**
     * Builds and sends a registration welcome email for supported entity types.
     * <p>
     * This method determines the entity type (e.g., {@link Student} or {@link Teacher}),
     * prepares the email template by replacing placeholders, and then sends the email.
     * </p>
     *
     * <p><b>Supported entities:</b></p>
     * <ul>
     *     <li>{@link Student}</li>
     *     <li>{@link Teacher}</li>
     * </ul>
     *
     * @param entity the entity representing the user being registered
     * @throws UnsupportedRegistrationEntityException if the entity type is not supported
     */
    @Override
    public void buildEmailSendRegister(Object entity) {
        Map<String, String> placeholders = EmailUtil.registrationPlaceholdersBuild(entity);

        String htmlTemplate = EmailUtil.loadTemplate(EmailConstants.EMAIL_TEMPLATE_REGISTRATION_PATH);

        String formattedText = EmailUtil.formatTemplate(
                htmlTemplate,
                placeholders
        );

        if (entity instanceof Student student) {
            sendWelcomeEmail(student.getEmail(), EmailConstants.EMAIL_REGISTRATION_SUBJECT, formattedText);
        } else if (entity instanceof Teacher teacher) {
            sendWelcomeEmail(teacher.getEmail(), EmailConstants.EMAIL_REGISTRATION_SUBJECT, formattedText);
        } else {
            throw new UnsupportedRegistrationEntityException(entity.getClass());
        }
    }
}
