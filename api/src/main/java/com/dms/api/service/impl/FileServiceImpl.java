package com.dms.api.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.api.dto.FileDto;
import com.dms.api.model.FileEntity;
import com.dms.api.repository.FileRepository;
import com.dms.api.service.FileService;

import jakarta.annotation.Resource;

@Service
public class FileServiceImpl implements FileService {

  @Resource
  private FileRepository repository;
  @Autowired
  private ModelMapper modelMapper;

  @Override
  public FileEntity saveData(FileDto fileDto) {

    FileEntity fileEntity = this.modelMapper.map(fileDto, FileEntity.class);
    fileEntity = repository.save(fileEntity);
    return fileEntity;
  }
  
}
