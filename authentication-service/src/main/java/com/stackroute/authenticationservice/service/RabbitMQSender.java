package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.model.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class RabbitMQSender {
    @NonNull
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    @Value("${spring.rabbitmq.routingkey1}")
    private String routingkey1;

    public void send(User user) {

        if(user.getRole()== User.Role.JOBSEEKER) {

            rabbitTemplate.convertAndSend(exchange, routingkey, user.getEmailid());
        }
        else
            rabbitTemplate.convertAndSend(exchange,routingkey1,user.getEmailid());
    }
}
