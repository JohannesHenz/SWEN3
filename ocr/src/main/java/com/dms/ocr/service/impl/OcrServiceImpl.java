package com.dms.ocr.service.impl;

import java.util.LinkedList;
import java.util.List;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.ghost4j.document.DocumentException;
import org.ghost4j.document.PDFDocument;
import org.ghost4j.renderer.RendererException;
import org.ghost4j.renderer.SimpleRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.ocr.dto.FileDto;
import com.dms.ocr.rabbitmq.RabbitMQProducer;
import com.dms.ocr.service.OcrService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Slf4j
@Service
public class OcrServiceImpl implements OcrService {

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    @Override
    public void doOCR(FileDto fileDto) {
        File file = new File(fileDto.getFilePath());
        if (file.exists()) {
            StringBuffer textBuffer = new StringBuffer();
            List<BufferedImage> bufferedImages = covertToImage(file);
            System.out.println("bufferedImages: " + bufferedImages.size());
            for (BufferedImage image : bufferedImages) {
                try {
                    Tesseract tesseract = new Tesseract();
                    tesseract.setLanguage("eng");
                    tesseract.setDatapath("/opt/");
                    tesseract.setTessVariable("user_defined_dpi", "96");

                    String convertedText = tesseract.doOCR(image);
                    log.info("\n-----------------File ID" + fileDto.getId());
                    log.info(convertedText);
                    log.info("\n-----------------");
                    textBuffer.append("\n").append(convertedText);
                } catch (TesseractException e) {
                    log.error(e.getMessage(), e);
                }
            }
            fileDto.setTextContent(textBuffer.toString());
        } else {
            fileDto.setTextContent("File not found");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            rabbitMQProducer.sendMessage(objectMapper.writeValueAsString(fileDto));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }

  
    private List<BufferedImage> covertToImage(File pdfDoc) {
        PDFDocument document = new PDFDocument();
        List<BufferedImage> bufferedImages = new LinkedList<>();
        try {
            document.load(pdfDoc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SimpleRenderer renderer = new SimpleRenderer();
        renderer.setResolution(300);
        
        List<Image> images = null;
        try {
            images = renderer.render(document);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RendererException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        log.info("\nimages: " + images);
            if (images != null) {
                for (Image image : images) {
                    BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D bGr = bimage.createGraphics();
                    bGr.drawImage(image, 0, 0, null);
                    bGr.dispose();
                    bufferedImages.add(bimage);
                }
            }
        return bufferedImages;
    }

}
