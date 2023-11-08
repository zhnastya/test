package com.example.test.exeptions;

public class ValidationExeption extends RuntimeException{
    public ValidationExeption(String message) {
        super(message);
    }
}
