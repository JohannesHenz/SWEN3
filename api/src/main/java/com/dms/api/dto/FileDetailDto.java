package com.dms.api.dto;

import lombok.Data;

@Data
public class FileDetailDto {
  
  private Long id;

  private String fileName;

  private Long fileSize;

  private String textContent;

  private Integer status;

}
