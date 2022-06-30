package com.gymworkouts.gymworkouts.Listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RegisterListener {

    @RabbitListener(queues = "register-queue")
    public void handleRegisterQueue(String message) {
        System.out.println("Message: " + message);
    }
}
