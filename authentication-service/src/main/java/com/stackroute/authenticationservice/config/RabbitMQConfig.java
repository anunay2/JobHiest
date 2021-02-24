package com.stackroute.authenticationservice.config;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
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

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    @Value("${spring.rabbitmq.routingkey1}")
    private String routingkey1;

    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @Value("${spring.rabbitmq.queue1}")
    private String queue1;

    @Bean
    Queue queue(){
        return new Queue(queue,true);
    }
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
                .bind(queue())
                .to(myExchange())
                .with(routingkey)
                .noargs();
    }
    @Bean
    Binding binding1(){
        return BindingBuilder
                .bind(queue1())
                .to(myExchange())
                .with(routingkey1)
                .noargs();
    }
}
