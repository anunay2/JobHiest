package com.stackroute.profileindexing.config;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    /**
     * providing host, username, password to connect with rabbitmq server
     */

    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;

    /*
     *providing queues for publishing messages
     */
    @Value("${spring.rabbitmq.personalQ}")
    private String personalQ;
    @Value("${spring.rabbitmq.experienceQ}")
    private String experienceQ;
    @Value("${spring.rabbitmq.skillQ}")
    private String skillQ;
    @Value("${spring.rabbitmq.indexerExchange}")

    //Config for exchange creation
    private String exchange;
    @Value("${spring.rabbitmq.personalRoute}")

    /*
     * providing configuration for routing keys
     */
    private String personalKey;
    @Value("${spring.rabbitmq.experienceRoute}")
    private String experienceKey;
    @Value("${spring.rabbitmq.skillRoute}")
    private String skillKey;


    @Bean
    Queue personalqueue() {
        return new Queue(personalQ, true);
    }

    @Bean
    Queue experiencequeue() {
        return new Queue(experienceQ, true);
    }

    @Bean
    Queue skillqueue() {
        return new Queue(skillQ, true);
    }

    @Bean
    Exchange myExchange() {
        /**
         * Add code to create Direct Exchange
         */
        return ExchangeBuilder.directExchange(exchange).durable(true).build();
    }


    @Bean
    Binding personalbinding() {
        /**
         * Add code to bind queue and Exchange
         */
        return BindingBuilder.bind(personalqueue()).to(myExchange()).with(personalKey).noargs();
    }

    @Bean
    Binding experiencebinding() {
        /**
         * Add code to bind queue and Exchange
         */
        return BindingBuilder.bind(experiencequeue()).to(myExchange()).with(experienceKey).noargs();
    }

    @Bean
    Binding skillbinding() {
        /**
         * Add code to bind queue and Exchange
         */
        return BindingBuilder.bind(skillqueue()).to(myExchange()).with(skillKey).noargs();
    }
    @Bean
    ConnectionFactory connectionFactory() {
        /**
         * Add code to create connection to rabbitMq broker
         */
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        /*
         * Jackson2JsonMessageConverter to send the message in a JSON format.
         */
//        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
