package com.dms.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dms.api.dto.FileDetailDto;
import com.dms.api.dto.FileListDto;
import com.dms.api.dto.response.TestResponse;
import com.dms.api.model.FileEntity;
import com.dms.api.service.FileService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping
public class DmsController {

  @Autowired
  private FileService fileService;

  @GetMapping
  public ResponseEntity<TestResponse> test() {
    TestResponse testResponse = new TestResponse();
    testResponse.setMessage("This is DMS api");
    return ResponseEntity.ok().body(testResponse);
  }

  @GetMapping("/list")
  public ResponseEntity<List<FileListDto>> getList() {
    return ResponseEntity.ok().body(fileService.getList());
  }

  @GetMapping("/detail/{id}")
  public ResponseEntity<FileDetailDto> getDetail(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok().body(fileService.getDetail(id));
  }

  @PostMapping("/upload")
  public ResponseEntity<FileEntity> upload(@RequestParam("file") MultipartFile file) {
    
    return ResponseEntity.ok().body(fileService.uploadFileForOcr(file));
  }
  
  @PostMapping("/ocr")
  public ResponseEntity<FileEntity> ocr(@RequestParam("file") MultipartFile file) {
    return ResponseEntity.ok().body(fileService.uploadFileForOcr(file));
  }

}
