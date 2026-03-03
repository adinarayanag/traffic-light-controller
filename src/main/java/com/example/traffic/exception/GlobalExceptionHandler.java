
package com.example.traffic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

 @ExceptionHandler(IntersectionNotFoundException.class)
 @ResponseStatus(HttpStatus.NOT_FOUND)
 public Map<String,Object> notFound(Exception ex){
   return Map.of("timestamp", LocalDateTime.now(), "status",404,"error",ex.getMessage());
 }

 @ExceptionHandler(ConflictingGreenLightException.class)
 @ResponseStatus(HttpStatus.CONFLICT)
 public Map<String,Object> conflict(Exception ex){
   return Map.of("timestamp", LocalDateTime.now(), "status",409,"error",ex.getMessage());
 }

 @ExceptionHandler(TrafficSystemPausedException.class)
 @ResponseStatus(HttpStatus.BAD_REQUEST)
 public Map<String,Object> paused(Exception ex){
   return Map.of("timestamp", LocalDateTime.now(), "status",400,"error",ex.getMessage());
 }
}
