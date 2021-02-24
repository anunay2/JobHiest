package com.personal_info_processing_services.service;

import com.personal_info_processing_services.domain.Gender;
import com.personal_info_processing_services.domain.PersonalInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;

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
    void testReceivedMessage() {
        rabbitMqService.receivedMessage(new PersonalInfo("email", "name", Gender.Male, "dob", "currentLocation", "preferredLocation"));
    }

    @Test
    void testConfigureRabbitListeners() {
        rabbitMqService.configureRabbitListeners(null);
    }
}

