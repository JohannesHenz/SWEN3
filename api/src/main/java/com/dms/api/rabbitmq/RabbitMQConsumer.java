package com.dms.api.rabbitmq;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.dms.api.dto.FileDto;
import com.dms.api.service.FileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RabbitMQConsumer {
  
  @Autowired
  private FileService fileService;

  @RabbitListener
  (queues = "dms-ocr-complete-queue")
  public void receiveMessage(String message, Channel channel,
        @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
    try {
      channel.basicAck(tag, false);
    } catch (IOException e) {
      e.printStackTrace();
    }
    ObjectMapper objectMapper = new ObjectMapper();
    FileDto fileDto;
    try {
      fileDto = objectMapper.readValue(message, FileDto.class);
      log.info("Received message: " + fileDto);
      fileService.saveOcrData(fileDto);
      
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

}
