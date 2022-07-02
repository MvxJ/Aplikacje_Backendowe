package com.gymworkouts.gymworkouts.Service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Autowired
    private RabbitTemplate template;

    public void sendMessage(String email) {
        this.template.convertAndSend(
                "register-queue",
                email
        );
    }
}
