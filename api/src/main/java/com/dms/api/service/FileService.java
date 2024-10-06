package com.dms.api.service;

import com.dms.api.dto.FileDto;
import com.dms.api.model.FileEntity;

public interface FileService {
  
  FileEntity saveData(FileDto fileDto);

}
