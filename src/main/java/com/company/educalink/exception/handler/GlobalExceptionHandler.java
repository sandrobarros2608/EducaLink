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

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handling validation errors
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

    // Teacher doesn't found
    @ExceptionHandler(TeacherNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerTeacherNotFoundException(
            TeacherNotFoundException ex,
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

    // Student doesn't found
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerStudentNotFoundException(
            StudentNotFoundException ex,
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

    // Course doesn't found
    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerCourseNotFoundException(
            CourseNotFoundException ex,
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

    // Grade doesn't found
    @ExceptionHandler(GradeNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerGradeNotFoundException(
            GradeNotFoundException ex,
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

    // Announcement doesn't found
    @ExceptionHandler(AnnouncementNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerAnnouncementNotFoundException(
            AnnouncementNotFoundException ex,
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

    // Publication doesn't found
    @ExceptionHandler(PublicationNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerPublicationNotFoundException(
            PublicationNotFoundException ex,
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

    // Comment doesn't found
    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerCommentNotFoundException(
            CommentNotFoundException ex,
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
}
