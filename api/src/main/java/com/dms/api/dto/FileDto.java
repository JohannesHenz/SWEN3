package com.dms.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FileDto {
  
  @NotBlank(message = "fileName is required")
  private String fileName;

  @NotNull(message = "fileSize is required")
  private Long fileSize;

}
