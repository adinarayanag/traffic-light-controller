package com.example.traffic.exception;

import com.example.traffic.dto.ValidationErrorResponse;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1️⃣ Validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest()
                .body(new ValidationErrorResponse("Validation failed", errors));
    }

    // 2️⃣ Business conflict (409)
    @ExceptionHandler(ConflictingGreenLightException.class)
    public ResponseEntity<?> handleConflict(ConflictingGreenLightException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of(
                        "error", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }

    // 3️⃣ Illegal arguments (400)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegal(IllegalArgumentException ex) {

        return ResponseEntity.badRequest()
                .body(Map.of(
                        "error", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }

    // 4️⃣ Catch-all (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "error", "Unexpected error occurred",
                        "timestamp", LocalDateTime.now()
                ));
    }
}