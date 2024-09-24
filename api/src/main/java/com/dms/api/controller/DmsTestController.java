package com.dms.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dms.api.response.TestResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping
public class DmsTestController {
  
  @GetMapping
  public ResponseEntity<TestResponse> test() {
    TestResponse testResponse = new TestResponse();
    testResponse.setMessage("This is DMS api");
    return ResponseEntity.ok().body(testResponse);
  }
  

}
