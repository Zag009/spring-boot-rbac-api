package com.rbac.api.exception;

import com.rbac.api.dto.Dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exceptions.ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(Exceptions.ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exceptions.AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthentication(Exceptions.AuthenticationException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "Unauthorized", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(Exceptions.AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(Exceptions.AccessDeniedException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Forbidden", ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(Exceptions.DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResource(Exceptions.DuplicateResourceException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(), "Conflict", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(Exceptions.ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidation(Exceptions.ValidationException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", "An unexpected error occurred");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
