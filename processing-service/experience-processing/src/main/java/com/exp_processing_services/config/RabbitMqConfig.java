package com.exp_processing_services.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingKey;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.indexerQueue}")
    private String indexerqueue;

    @Value("${spring.rabbitmq.indexerExchange}")
    private String indexerexchange;

    @Value("${spring.rabbitmq.indexerRoutingKey}")
    private String indexerroutingKey;


    @Bean
    Queue queue() {

        return new Queue(queue, true);
    }

    @Bean
    DirectExchange myExchange() {

        return new DirectExchange(exchange);
    }

    @Bean
    Binding binding() {

        return BindingBuilder
                .bind(queue()).to(myExchange()).with(routingKey);
    }

    @Bean
    Queue indexerSkillQueue(){
        return new Queue(indexerqueue, true);
    }

    @Bean
    DirectExchange indexerExchange() {
        return new DirectExchange(indexerexchange);
    }

    @Bean
    Binding indexerBinding() {
        return BindingBuilder.bind(indexerSkillQueue()).to(indexerExchange()).with(indexerroutingKey);
    }


    @Bean
    CachingConnectionFactory connectionFactory() {

        CachingConnectionFactory cachingConnectionFactory= new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);

        return  cachingConnectionFactory;
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
