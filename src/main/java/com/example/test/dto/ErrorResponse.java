package com.example.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private final String error;
    private String stackTrace;

    public ErrorResponse(String error) {
        this.error = error;
    }
}
