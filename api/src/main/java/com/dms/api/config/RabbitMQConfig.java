package com.dms.api.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
  
  @Value("${broker.exchange}")
  private String exchange;
  @Value("${broker.routingKey}")
  private String routingKey;
  
  @Value("${broker.routingKeyOcrComplete}")
  private String routingKeyOcrComplete;
  


  @Bean 
  public Queue queueForOcr() {
    return new Queue("dms-for-ocr-queue", false);
  }

  @Bean 
  public Queue queueOcrComplete() {
    return new Queue("dms-ocr-complete-queue", false);
  }

  @Bean 
  public Exchange exchange() {
    return new DirectExchange(exchange);
  }

  @Bean
  public Binding bindingForOcr(Exchange exchange) {
    return BindingBuilder.bind(queueForOcr())
        .to(exchange)
        .with(routingKey)
        .noargs();
  }

  @Bean
  public Binding bindingOcrComplete(Exchange exchange) {
    return BindingBuilder.bind(queueOcrComplete())
        .to(exchange)
        .with(routingKeyOcrComplete)
        .noargs();
  }

}
