
package com.example.traffic.model;

import java.time.LocalDateTime;

public class TrafficLightState {
    private Direction direction;
    private LightColor color;
    private LocalDateTime changedAt;

    public TrafficLightState(Direction direction, LightColor color) {
        this.direction = direction;
        this.color = color;
        this.changedAt = LocalDateTime.now();
    }

    public Direction getDirection() { return direction; }
    public LightColor getColor() { return color; }
    public LocalDateTime getChangedAt() { return changedAt; }

    public void setColor(LightColor color) {
        this.color = color;
        this.changedAt = LocalDateTime.now();
    }
}
