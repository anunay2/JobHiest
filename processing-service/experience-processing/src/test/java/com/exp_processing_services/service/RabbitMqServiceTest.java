package com.exp_processing_services.service;

import com.exp_processing_services.domain.Experience;
import com.exp_processing_services.domain.ExperienceProcessing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.Arrays;

import static org.mockito.Mockito.*;

class RabbitMqServiceTest {
    @Mock
    Logger logger;
    @Mock
    AmqpTemplate amqpTemplate;
    @InjectMocks
    RabbitMqService rabbitMqService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Disabled
    void testReceivedMessage() {
        rabbitMqService.receivedMessage(new ExperienceProcessing("email", Arrays.<Experience>asList(new Experience(0, null, null, null, null, null, null, null, 0))));
    }

    @Test
    @Disabled
    void testConfigureRabbitListeners() {
        rabbitMqService.configureRabbitListeners(null);
    }
}

