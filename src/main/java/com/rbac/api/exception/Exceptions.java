package com.rbac.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class Exceptions {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }

        public ResourceNotFoundException(String resource, String field, Object value) {
            super(String.format("%s not found with %s: '%s'", resource, field, value));
        }
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public static class AuthenticationException extends RuntimeException {
        public AuthenticationException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    public static class AccessDeniedException extends RuntimeException {
        public AccessDeniedException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class DuplicateResourceException extends RuntimeException {
        public DuplicateResourceException(String message) {
            super(message);
        }

        public DuplicateResourceException(String resource, String field, Object value) {
            super(String.format("%s already exists with %s: '%s'", resource, field, value));
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class ValidationException extends RuntimeException {
        public ValidationException(String message) {
            super(message);
        }
    }
}
