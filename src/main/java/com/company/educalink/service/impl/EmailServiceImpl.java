package com.company.educalink.service.impl;

import com.company.educalink.constant.EmailConstants;
import com.company.educalink.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link EmailService} interface for sending HTML email messages.
 * <p>
 * This service uses Spring's {@link JavaMailSender} to construct and send
 * rich-text emails with embedded images (like the EducaLink logo).
 * </p>
 *
 * The method includes exception handling to manage possible issues when sending emails.
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Sends an HTML email message with the provided recipient, subject, and content.
     * <p>
     * The message includes the embedded EducaLink logo, and supports UTF-8 encoding.
     * </p>
     *
     * @param to           the recipient's email address
     * @param subject      the subject line of the email
     * @param htmlContent  the HTML-formatted content of the email
     * @throws IllegalStateException if the email could not be sent due to a messaging error
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
}
