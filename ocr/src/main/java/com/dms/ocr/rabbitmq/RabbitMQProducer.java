package com.dms.ocr.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer {
  
  @Value("${broker.exchange}")
  private String exchange;
  
  @Value("${broker.routingKeyOcrComplete}")
  private String routingKey;


  @Autowired
  private RabbitTemplate rabbitTemplate;

  public void sendMessage(String message) {
    rabbitTemplate.convertAndSend(exchange, routingKey, message);
  }

}
