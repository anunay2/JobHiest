package com.stackroute.jobprofileservice.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${spring.rabbitmq.host}")
    String host;

    @Value("${spring.rabbitmq.username}")
    String username;

    @Value("${spring.rabbitmq.password}")
    String password;

    @Value("${spring.rabbitmq.exchange_username}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey_username}")
    private String routingkey;

    @Value("${spring.rabbitmq.queue_username}")
    private String queue;

    @Bean
    Queue queue(){
        return new Queue(queue,true);
    }
    @Bean
    Exchange myExchange(){
        return ExchangeBuilder.directExchange(exchange).durable(true).build();
    }
    @Bean
    Binding binding(){
        return BindingBuilder
                .bind(queue())
                .to(myExchange())
                .with(routingkey)
                .noargs();
    }
}
