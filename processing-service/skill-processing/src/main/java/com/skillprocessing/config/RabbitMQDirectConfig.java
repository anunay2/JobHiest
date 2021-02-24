package com.skillprocessing.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQDirectConfig {
    @Value("${spring.rabbitmq.indexerQueue}")
    private String queue;

    @Value("${spring.rabbitmq.indexerExchange}")
    private String exchange;

    @Value("${spring.rabbitmq.indexerRoutingKey}")
    private String routingKey;

    @Bean
    Queue indexerSkillQueue(){
        return new Queue(queue, true);
    }

    @Bean
    DirectExchange indexerExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Binding indexerBinding() {
        return BindingBuilder.bind(indexerSkillQueue()).to(indexerExchange()).with(routingKey);
    }
}
