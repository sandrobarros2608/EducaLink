package com.company.educalink.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationErrorDTO {
    private String field;
    private String message;
}
