package com.example.traffic.dto;

import com.example.traffic.model.Direction;
import com.example.traffic.model.LightColor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Request to change traffic light state")
public class ChangeLightRequest {

    @NotNull
    @Schema(example = "NORTH_SOUTH")
    private Direction direction;

    @NotNull
    @Schema(example = "GREEN")
    private LightColor color;

    public Direction getDirection() { return direction; }
    public void setDirection(Direction direction) { this.direction = direction; }

    public LightColor getColor() { return color; }
    public void setColor(LightColor color) { this.color = color; }
}