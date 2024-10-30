package com.dms.api.dto;

import lombok.Data;

@Data
public class FileListDto {
  
  private Long id;

  private String fileName;

  private Long fileSize;

  private Integer status;

}
