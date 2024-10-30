package com.dms.api.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dms.api.dto.FileDetailDto;
import com.dms.api.dto.FileDto;
import com.dms.api.dto.FileListDto;
import com.dms.api.model.FileEntity;

public interface FileService {
  
  List<FileListDto> getList();

  FileDetailDto getDetail(Long id);

  void saveOcrData(FileDto fileDto);

  FileEntity uploadFileForOcr(MultipartFile multipartFile);


}
