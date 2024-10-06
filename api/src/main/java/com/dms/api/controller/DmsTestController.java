package com.dms.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dms.api.dto.FileDto;
import com.dms.api.dto.response.TestResponse;
import com.dms.api.model.FileEntity;
import com.dms.api.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping
public class DmsTestController {

  @Autowired
  private FileService testService;
  
  @GetMapping
  public ResponseEntity<TestResponse> test() {
    TestResponse testResponse = new TestResponse();
    testResponse.setMessage("This is DMS api");
    return ResponseEntity.ok().body(testResponse);
  }

  @PostMapping("/file")
  public ResponseEntity<FileEntity> save(@RequestBody @Validated FileDto fileDto) {
    return ResponseEntity.ok().body(testService.saveData(fileDto));
  }
  
  

}
