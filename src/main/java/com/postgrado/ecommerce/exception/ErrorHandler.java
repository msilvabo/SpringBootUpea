package com.postgrado.ecommerce.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(Exception e){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse error = ErrorResponse.builder()
                .code(status.value())
                .error(status.name())
                .message(e.getMessage()
                )
                .build();
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(EmailAlreadyUsed.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyTakenException(Exception e){
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorResponse error = ErrorResponse.builder()
                .code(status.value())
                .error(status.name())
                .message(e.getMessage()
                )
                .build();
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException (Exception e){
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ErrorResponse error = ErrorResponse.builder()
                .code(status.value())
                .error(status.name())
                .message(e.getMessage()
                )
                .build();
        return ResponseEntity.status(status).body(error);
    }
}

