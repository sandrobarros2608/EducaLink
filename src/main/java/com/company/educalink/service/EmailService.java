package com.company.educalink.service;

import java.util.Date;

public interface EmailService {

    void sendHtmlMessage(String to, String subject, String htmlContent);
}
