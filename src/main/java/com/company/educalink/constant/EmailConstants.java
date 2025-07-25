package com.company.educalink.constant;

public class EmailConstants {

    // ===================== Email Configuration =====================
    public static final String EMAIL_FROM_TO = "noreply@educalink.com";
    public static final String EMAIL_REGISTRATION_SUBJECT = "Welcome To EducaLink!";
    public static final String EMAIL_REGISTRATION_TEXT = "Dear {Name},\n" +
            "\n" +
            "Thank you for registering with EducaLink.\n" +
            "\n" +
            "We've noticed that a new account was recently created using your email address.\n" +
            "\n" +
            "To complete your registration, please verify your email as soon as possible.\n" +
            "\n" +
            "If you did not initiate this request, feel free to ignore this message.\n" +
            "\n" +
            "Sincerely,\n" +
            "\n" +
            "The EducaLink Team";
    public static final String EMAIL_TEMPLATE_REGISTRATION_PATH = "templates/email/EmailRegistrationText.html";
    public static final String EMAIL_LOGO_PATH = "static/images/educalinklogo.png";
    public static final String EMAIL_LOGO_CONTENT_ID = "educalinkLogo";
}
