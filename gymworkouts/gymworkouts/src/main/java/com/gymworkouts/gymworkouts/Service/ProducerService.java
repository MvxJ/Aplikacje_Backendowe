package com.gymworkouts.gymworkouts.Service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Autowired
    private RabbitTemplate template;

    public void sendMessage(String message) {
        this.template.convertAndSend("register-queue", message);
    }
}
