package com.postgrado.ecommerce.exception;

import com.postgrado.ecommerce.exception.response.ErrorResponse;
import com.postgrado.ecommerce.exception.response.FieldErrorModel;
import com.postgrado.ecommerce.exception.response.ValidationErrorResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValidException (MethodArgumentNotValidException e){

        List<FieldErrorModel> errors = e.getBindingResult().getAllErrors().stream().map(fieldError -> {
            FieldErrorModel fieldErrorModel = new FieldErrorModel();
            fieldErrorModel.setCode(fieldError.getCode());
            fieldErrorModel.setMessage(fieldError.getDefaultMessage());
            fieldErrorModel.setField(((FieldError)fieldError).getField());
            return fieldErrorModel;
        }).toList();

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ValidationErrorResponse response = new ValidationErrorResponse();
        response.setCode(status.value());
        response.setErrors(errors);
        return ResponseEntity.status(status).body(response);
    }
}

