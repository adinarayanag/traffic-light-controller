package com.example.traffic.controller;

import com.example.traffic.dto.*;
import com.example.traffic.model.TrafficLightState;
import com.example.traffic.service.TrafficLightService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.media.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Traffic Management", description = "Traffic Light Control APIs")
@RestController
@RequestMapping("/api/v1/traffic")
public class TrafficLightController {

    private final TrafficLightService service;

    public TrafficLightController(TrafficLightService service) {
        this.service = service;
    }

    @Operation(summary = "Create intersection")
    @PostMapping("/{id}")
    public ResponseEntity<ApiSuccessResponse> create(@PathVariable String id) {
        service.createIntersection(id);
        return ResponseEntity.ok(new ApiSuccessResponse("Intersection created"));
    }

    @Operation(summary = "Change traffic light state")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Light changed successfully",
                    content = @Content(schema = @Schema(implementation = ApiSuccessResponse.class)))
    })
    @PostMapping("/{id}/lights")
    public ResponseEntity<ApiSuccessResponse> changeLight(
            @PathVariable String id,
            @Valid @RequestBody ChangeLightRequest request) {

        service.changeLight(id, request.getDirection(), request.getColor());

        return ResponseEntity.ok(new ApiSuccessResponse("Light updated successfully"));
    }

    @Operation(summary = "Pause intersection")
    @PostMapping("/{id}/pause")
    public ResponseEntity<ApiSuccessResponse> pause(@PathVariable String id) {
        service.pause(id);
        return ResponseEntity.ok(new ApiSuccessResponse("Intersection paused"));
    }

    @Operation(summary = "Resume intersection")
    @PostMapping("/{id}/resume")
    public ResponseEntity<ApiSuccessResponse> resume(@PathVariable String id) {
        service.resume(id);
        return ResponseEntity.ok(new ApiSuccessResponse("Intersection resumed"));
    }

    @Operation(summary = "Get current traffic state")
    @GetMapping("/{id}/lights")
    public ResponseEntity<List<LightStateResponse>> state(@PathVariable String id) {

        List<LightStateResponse> response = service.getState(id)
                .values()
                .stream()
                .map(this::mapToResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get history")
    @GetMapping("/{id}/history")
    public ResponseEntity<List<LightStateResponse>> history(@PathVariable String id) {

        List<LightStateResponse> response = service.getHistory(id)
                .stream()
                .map(this::mapToResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    private LightStateResponse mapToResponse(TrafficLightState state) {
        return new LightStateResponse(
                state.getDirection().name(),
                state.getColor().name(),
                state.getChangedAt());
    }
}