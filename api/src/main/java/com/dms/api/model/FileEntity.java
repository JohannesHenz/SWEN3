package com.dms.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "file")
public class FileEntity {

  @Id
  @GeneratedValue
  private Long id;

  @NotBlank(message = "fileName is required")
  private String fileName;

  @NotNull(message = "fileSize is required")
  private Long fileSize;

  @Column(columnDefinition = "text", length=10485760)
  private String textContent;

  private Integer status;


}
