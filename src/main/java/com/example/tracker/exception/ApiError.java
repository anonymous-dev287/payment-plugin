package com.example.tracker.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
	
    private final LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;

    public ApiError(int status, String error, String message) {
        this(LocalDateTime.now(), status, error, message);
    }
}
