package com.example.test.controllers;

import com.example.test.dto.ErrorResponse;
import com.example.test.exeptions.ValidationExeption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(final ValidationExeption e) {
        log.warn(e.getMessage());
        return new ErrorResponse(
                String.format("Ошибка валидации:  \"%s\".", e.getMessage())
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleThrowable(final Throwable e) {
        log.error("Error: ", e);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String stack = sw.toString();
        return new ErrorResponse(
                "Произошла непредвиденная ошибка.", stack
        );
    }
}
