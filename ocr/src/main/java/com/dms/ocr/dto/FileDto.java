package com.dms.ocr.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FileDto {
  
  private Long id;

  @NotNull(message = "filePath is required")
  private String filePath;

  private String textContent;

}
