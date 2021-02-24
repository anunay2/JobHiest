package com.stackroute.jobrecruiterprofileservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
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

    @Value("${spring.rabbitmq.exchange_recruitername}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey_recruitername}")
    private String routingkey1;

    @Value("${spring.rabbitmq.queue_recruitername}")
    private String queue1;

    @Bean
    Queue queue1(){
        return new Queue(queue1,true);
    }
    @Bean
    Exchange myExchange(){
        return ExchangeBuilder.directExchange(exchange).durable(true).build();
    }
    @Bean
    Binding binding(){
        return BindingBuilder
                .bind(queue1())
                .to(myExchange())
                .with(routingkey1)
                .noargs();
    }
    @Bean
    CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
