package com.dms.api.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dms.api.dto.FileDetailDto;
import com.dms.api.dto.FileDto;
import com.dms.api.dto.FileListDto;
import com.dms.api.model.FileEntity;
import com.dms.api.rabbitmq.RabbitMQProducer;
import com.dms.api.repository.FileRepository;
import com.dms.api.service.FileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

  @Resource
  private FileRepository repository;
  @Autowired
  private ModelMapper modelMapper;
  @Autowired 
  private RabbitMQProducer rabbitMQProducer;
  
  private String BASE_PATH = "/tmp/dms/documents";

  @PostConstruct
  void init() {
    new File(BASE_PATH).mkdirs();
  }


  @Override
  public List<FileListDto> getList() {
    List<FileListDto> list = new ArrayList<>();
    repository.findAll().iterator().forEachRemaining(it -> {
      list.add(modelMapper.map(it, FileListDto.class));
    });
    return list;
  }

  @Override
  public FileDetailDto getDetail(Long id) {
    Optional<FileEntity> fileEntityOp = repository.findById(id);
    if (fileEntityOp.isPresent()) {
      FileEntity fileEntity = fileEntityOp.get();
      return modelMapper.map(fileEntity, FileDetailDto.class);
    }
    return null;
  }

  @Override
  public void saveOcrData(FileDto fileDto) {

    Optional<FileEntity> fileEntityOp = repository.findById(fileDto.getId());
    if (fileEntityOp.isPresent()) {
      FileEntity fileEntity = fileEntityOp.get();
      fileEntity.setStatus(2);
      fileEntity.setTextContent(fileDto.getTextContent());
      repository.save(fileEntity);
    } else {
      log.error("File record not found for file Id:" + fileDto.getId());
    }
  }


  @Override
  public FileEntity uploadFileForOcr(MultipartFile multipartFile) {
    File file = new File(BASE_PATH + "/" + multipartFile.getOriginalFilename());
    try {
      multipartFile.transferTo(file);
      FileEntity fileEntity = new FileEntity();
      fileEntity.setFileName(multipartFile.getOriginalFilename());
      fileEntity.setFileSize(multipartFile.getSize());
      fileEntity.setStatus(1);
      repository.save(fileEntity);

      FileDto fileDto = modelMapper.map(fileEntity, FileDto.class);
      fileDto.setId(fileEntity.getId());
      fileDto.setFilePath(BASE_PATH + "/" + multipartFile.getOriginalFilename());
      ObjectMapper objectMapper = new ObjectMapper();
      rabbitMQProducer.sendMessage(objectMapper.writeValueAsString(fileDto));
      return fileEntity;
    } catch (IllegalStateException | IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  
  
}
