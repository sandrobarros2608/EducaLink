package com.company.educalink.exception.handler;

import com.company.educalink.exception.custom.*;
import com.company.educalink.exception.dto.ErrorResponseDTO;
import com.company.educalink.exception.dto.ValidationErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handler for managing and formatting custom error responses
 * across the entire application.
 * <p>
 * Handles validation errors, not found resources, and duplicate email cases, returning
 * structured JSON responses for better client-side handling.
 * </p>
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation errors thrown by @Valid annotated request bodies.
     *
     * @param ex      the MethodArgumentNotValidException containing validation errors
     * @param request the HTTP request
     * @return a ResponseEntity with status 400 (Bad Request) and a structured error response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        List<ValidationErrorDTO> validationErrorDTO = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> new ValidationErrorDTO(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                request.getRequestURI(),
                validationErrorDTO
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles cases where a requested resource was not found in the system.
     *
     * @param ex      the ResourceNotFoundException thrown
     * @param request the HTTP request
     * @return a ResponseEntity with status 404 (Not Found) and a structured error response
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerResourceNotFoundException(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequestURI(),
                null
        );

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles cases where the provided email already exists in the database.
     *
     * @param ex      the DuplicateEmailException thrown
     * @param request the HTTP request
     * @return a ResponseEntity with status 409 (Conflict) and a structured error response
     */
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponseDTO> handlerDuplicateEmailException(
            DuplicateEmailException ex,
            HttpServletRequest request) {

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                request.getRequestURI(),
                null
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.CONFLICT);
    }
}
