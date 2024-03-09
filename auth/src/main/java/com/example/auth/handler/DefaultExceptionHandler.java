package com.example.auth.handler;

import com.example.auth.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> exceptionHandler(ConflictException exception, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                exception.getMessage(),
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }

    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<?> exceptionHandler(CustomBadRequestException exception, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(DefaultValidationException.class)
    public ResponseEntity<?> exceptionHandler(DefaultValidationException exception, HttpServletRequest request) {
        Map<String, Object> apiError = new HashMap<>();
        apiError.put("path", request.getRequestURI());
        apiError.put("message", exception.getMessage());
        apiError.put("statusCode", HttpStatus.BAD_REQUEST.value());
        apiError.put("dateTime", LocalDateTime.now());
        apiError.put("errors", exception.getFieldErrors());



        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(NoLongerExistsException.class)
    public ResponseEntity<?> exceptionHandler(NoLongerExistsException exception, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                exception.getMessage(),
                HttpStatus.GONE.value(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.GONE).body(apiError);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> exceptionHandler(NotFoundException exception, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

}
