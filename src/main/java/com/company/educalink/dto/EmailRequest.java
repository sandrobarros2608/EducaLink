package com.company.educalink.dto;

import lombok.Data;

@Data
public class EmailRequest {

    private String to;
    private String subject;
    private String text;
}
