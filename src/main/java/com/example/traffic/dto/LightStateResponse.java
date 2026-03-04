package com.example.traffic.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Traffic light state response")
public class LightStateResponse {

    @Schema(example = "NORTH_SOUTH")
    private String direction;

    @Schema(example = "GREEN")
    private String color;

    @Schema(example = "2026-03-03T14:20:10")
    private LocalDateTime changedAt;

    public LightStateResponse(String direction, String color, LocalDateTime changedAt) {
        this.direction = direction;
        this.color = color;
        this.changedAt = changedAt;
    }

    public String getDirection() { return direction; }
    public String getColor() { return color; }
    public LocalDateTime getChangedAt() { return changedAt; }
}