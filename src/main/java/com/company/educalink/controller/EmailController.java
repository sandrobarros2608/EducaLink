package com.company.educalink.controller;

import com.company.educalink.dto.EmailRequest;
import com.company.educalink.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for sending emails.
 * <p>
 * Provides an endpoint for sending HTML-based email messages.
 * </p>
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Tag(name = "Email", description = "Endpoint for sending email messages")
@RestController
@RequestMapping("/api/email")
public class EmailController {

    /**
     * Service for handling email sending operations.
     */
    @Autowired
    private EmailService emailService;

    /**
     * Sends an HTML email message.
     * <p>
     * Accepts recipient address, subject, and content through the request body.
     * </p>
     *
     * @param emailRequest the request containing email details
     * @return HTTP 200 with success confirmation
     */
    @Operation(summary = "Send an HTML email", description = "Sends an HTML email using the provided recipient, subject, and content.")
    @PostMapping
    public ResponseEntity<String> sendSimpleMessage(@RequestBody EmailRequest emailRequest) {
        emailService.sendHtmlMessage(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getText());
        return ResponseEntity.ok("Email send successfully!");
    }
}
