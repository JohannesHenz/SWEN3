package com.dms.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.dms.api.dto.FileDto;
import com.dms.api.model.FileEntity;

public class MapperTests {
  
  ModelMapper modelMapper = new ModelMapper();

  @Test
  void DtoToEntityMapperTest() {
    FileDto fileDto = new FileDto();
    fileDto.setFileName("Document.pdf");
    fileDto.setFileSize(1000L);

    FileEntity fileEntity = modelMapper.map(fileDto, FileEntity.class);
    assertEquals(fileDto.getFileName(), fileEntity.getFileName());
    assertEquals(fileDto.getFileSize(), fileEntity.getFileSize());
  }

}
