package com.nuzhd.exception;

import io.minio.errors.MinioException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(MinioException.class)
    public ResponseEntity<?> handleException(MinioException e) {
        return ResponseEntity
                .internalServerError()
                .body(e.getMessage());
    }

    @ExceptionHandler(InvalidKeyException.class)
    public ResponseEntity<?> handleException(InvalidKeyException e) {
        return ResponseEntity
                .internalServerError()
                .body(e.getMessage());
    }

    @ExceptionHandler(NoSuchAlgorithmException.class)
    public ResponseEntity<?> handleException(NoSuchAlgorithmException e) {
        return ResponseEntity
                .internalServerError()
                .body(e.getMessage());
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> handleException(IOException e) {
        return ResponseEntity
                .internalServerError()
                .body(e.getMessage());
    }

}
