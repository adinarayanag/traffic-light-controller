package com.example.traffic.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Standard API error response")
public class ErrorResponse {

    @Schema(description = "Timestamp of error", example = "2026-03-03T14:20:10")
    private LocalDateTime timestamp;

    @Schema(description = "HTTP status code", example = "409")
    private int status;

    @Schema(description = "Error message", example = "Conflicting GREEN direction")
    private String error;

    public ErrorResponse(LocalDateTime timestamp, int status, String error) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getError() { return error; }
}