package com.company.educalink.service;

import com.company.educalink.constant.EmailConstants;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendHtmlMessage(String to, String subject, String htmlContent) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(EmailConstants.EMAIL_FROM_TO);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            ClassPathResource image = new ClassPathResource("static/images/educalinklogo.png");
            helper.addInline("educalinkLogo", image);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new IllegalStateException("No se pudo enviar el correo", e);
        }
    }
}
