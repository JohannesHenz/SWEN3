package com.dms.ocr.service;

import com.dms.ocr.dto.FileDto;

/**
 * OcrService
 */
public interface OcrService {
  
  void doOCR(FileDto fileDto);
  
}