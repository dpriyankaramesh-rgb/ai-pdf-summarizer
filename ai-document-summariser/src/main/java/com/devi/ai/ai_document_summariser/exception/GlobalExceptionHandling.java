package com.devi.ai.ai_document_summariser.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(NoFileUploadedException.class)
    public ResponseEntity<String> handleFileLimitException(NoFileUploadedException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(FileLimitExceededException.class)
    public ResponseEntity<String> handleFileLimitException(FileLimitExceededException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<String> handleFileLimitException(InvalidFileException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
