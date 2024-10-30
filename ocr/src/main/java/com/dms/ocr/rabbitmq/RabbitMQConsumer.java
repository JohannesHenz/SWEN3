package com.dms.ocr.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.dms.ocr.dto.FileDto;
import com.dms.ocr.service.OcrService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RabbitMQConsumer {

  @Autowired
  private OcrService ocrService;
  
  @RabbitListener(queues = "dms-for-ocr-queue")
  public void receiveMessage(String message, Channel channel,
        @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
    try {
      channel.basicAck(tag, false);
    } catch (Exception e) {
      e.printStackTrace();
    }
    ObjectMapper objectMapper = new ObjectMapper();
    FileDto fileDto;
    try {
      System.out.println("filt dto for ocr message: " + message);
      fileDto = objectMapper.readValue(message, FileDto.class);
      ocrService.doOCR(fileDto);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
