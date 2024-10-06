package com.dms.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TestValidationRequest {
  
  private Long id;

  @NotBlank(message = "Data is necessary")
  private String data;

}
