package com.personal_info_processing_services.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

class RabbitMqConfigTest {
    RabbitMqConfig rabbitMqConfig = new RabbitMqConfig();

    @Test
    void testMyExchange() {
        DirectExchange result = rabbitMqConfig.myExchange();
        Assertions.assertNotNull(result);
    }



    @Test
    void testIndexerExchange() {
        DirectExchange result = rabbitMqConfig.indexerExchange();
        Assertions.assertNotNull(result);
    }

    @Test
    void testConnectionFactory() {
        CachingConnectionFactory result = rabbitMqConfig.connectionFactory();
        Assertions.assertNotNull(result);
    }

    @Test
    void testJsonMessageConverter() {
        Jackson2JsonMessageConverter result = rabbitMqConfig.jsonMessageConverter();
        Assertions.assertNotNull(result);
    }

    @Test
    void testRabbitTemplate() {
        RabbitTemplate result = rabbitMqConfig.rabbitTemplate(null);
        Assertions.assertNotNull(result);
    }
}