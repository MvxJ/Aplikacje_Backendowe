package com.gymworkouts.gymworkouts.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${gumworkouts.rabbitmq.exchange}")
    private String exchange;

    @Value("${gumworkouts.rabbitmq.routingkey}")
    private String routingkey;

    public void send(String email) {
        rabbitTemplate.convertAndSend(exchange, routingkey, email);
        System.out.println("Send message with:" + email);
    }
}
