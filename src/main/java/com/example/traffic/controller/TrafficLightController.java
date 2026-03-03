
package com.example.traffic.controller;

import com.example.traffic.model.*;
import com.example.traffic.service.TrafficLightService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/traffic")
public class TrafficLightController {

 private final TrafficLightService service;

 public TrafficLightController(TrafficLightService service){
   this.service = service;
 }

 @PostMapping("/create/{id}")
 public void create(@PathVariable String id){ service.createIntersection(id); }

 @PostMapping("/{id}/change")
 public void change(@PathVariable String id,
                    @RequestParam Direction direction,
                    @RequestParam LightColor color){
   service.changeLight(id,direction,color);
 }

 @PostMapping("/{id}/pause")
 public void pause(@PathVariable String id){ service.pause(id); }

 @PostMapping("/{id}/resume")
 public void resume(@PathVariable String id){ service.resume(id); }

 @GetMapping("/{id}/state")
 public Object state(@PathVariable String id){ return service.getState(id); }

 @GetMapping("/{id}/history")
 public Object history(@PathVariable String id){ return service.getHistory(id); }
}
