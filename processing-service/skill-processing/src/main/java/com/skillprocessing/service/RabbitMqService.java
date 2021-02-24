package com.skillprocessing.service;


import com.skillprocessing.model.UserSkills;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqService implements RabbitListenerConfigurer {

    private static final Logger logger= LoggerFactory.getLogger(RabbitMqService.class);
    @Autowired
    private SkillsProcessing skillsProcessing;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${spring.rabbitmq.indexerExchange}")
    private String exchange;

    @Value("${spring.rabbitmq.indexerRoutingKey}")
    private String routingKey;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(UserSkills userSkills){
        logger.info("userskills received"+userSkills);
        UserSkills updatedSkills = skillsProcessing.process(userSkills);
        logger.info("userskills received twice"+userSkills);
        amqpTemplate.convertAndSend(exchange, routingKey, updatedSkills);
        logger.info("userskills received thrice"+userSkills);
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}
