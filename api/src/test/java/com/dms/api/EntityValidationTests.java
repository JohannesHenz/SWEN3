package com.dms.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import org.junit.jupiter.api.Test;

import com.dms.api.model.FileEntity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;


public class EntityValidationTests {
  
  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  public void inValidEntityTest() {
    FileEntity fileEntity = new FileEntity();
    fileEntity.setFileName("Document.pdf");
    Set<ConstraintViolation<FileEntity>> violations = validator.validate(fileEntity);
    assertFalse(violations.isEmpty());
  }

  @Test
  public void validEntityTest() {
    FileEntity fileEntity = new FileEntity();
    fileEntity.setFileName("Document.pdf");
    fileEntity.setFileSize(1000L);
    Set<ConstraintViolation<FileEntity>> violations = validator.validate(fileEntity);
    assertTrue(violations.isEmpty());
  }

}
