package com.example.traffic.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Standard success response")
public class ApiSuccessResponse {

    @Schema(example = "Light updated successfully")
    private String message;

    public ApiSuccessResponse(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }
}