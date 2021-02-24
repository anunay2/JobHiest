package com.exp_processing_services.service;


import com.exp_processing_services.domain.Experience;
import com.exp_processing_services.domain.ExperienceProcessed;
import com.exp_processing_services.domain.ExperienceProcessing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void receivedMessage(ExperienceProcessing experience){
        logger.info("experience received "+experience);
        int sum=0;
        List<Experience> experienceList = experience.getExperiences();
        for (Experience experience1 : experienceList){
            sum+=experience1.getMonths();
        }
        ExperienceProcessed experienceProcessed = new ExperienceProcessed(experience.getEmail(),sum,experience.getExperiences());
        amqpTemplate.convertAndSend(exchange, routingKey, experienceProcessed);
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}
