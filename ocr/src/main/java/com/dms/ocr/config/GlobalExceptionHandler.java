package com.dms.ocr.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
  
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> globalExceptionClass(Exception e) {
    log.error(e.getMessage(), e);
    Map<String, Object> data = new HashMap<>();
    data.put("status", 500);
    data.put("message", e.getMessage());
    return ResponseEntity.internalServerError().body(data);
  }


}
