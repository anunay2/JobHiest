package com.personal_info_processing_services.service;


import com.personal_info_processing_services.domain.PersonalInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService implements RabbitListenerConfigurer {

    private static final Logger logger= LoggerFactory.getLogger(RabbitMqService.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${spring.rabbitmq.indexerExchange}")
    private String exchange;

    @Value("${spring.rabbitmq.indexerRoutingKey}")
    private String routingKey;

    @RabbitListener(queues="${spring.rabbitmq.queue}")
    public void receivedMessage(PersonalInfo pi1){
        logger.info("personal info received "+pi1);
        amqpTemplate.convertAndSend(exchange, routingKey, pi1);
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}
