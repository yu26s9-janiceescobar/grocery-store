package org.yearup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleResourceNotFoundException(ResourceNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("Message",ex.getMessage()));
    }
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistException(UserAlreadyExistException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("Message", ex.getMessage()));
    }
    @ExceptionHandler(InvalidStateException.class)
    public ResponseEntity<Map<String, String>> handleIncompleteProfileException(InvalidStateException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("Message", ex.getMessage()));
    }
}
