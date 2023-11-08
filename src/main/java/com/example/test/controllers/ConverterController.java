package com.example.test.controllers;

import com.example.test.exeptions.ValidationExeption;
import com.example.test.service.ConverterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ConverterController {
    private final ConverterService service;

    @Operation(summary = "Convert a new String")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Converted string"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @PostMapping("/convert")
    public Map<Character, Long> getMap(@RequestBody String text) {
        return service.getSortMap(text);
    }
}
