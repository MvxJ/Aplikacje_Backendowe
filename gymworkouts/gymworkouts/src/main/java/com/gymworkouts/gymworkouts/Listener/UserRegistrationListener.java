package com.gymworkouts.gymworkouts.Listener;

import com.gymworkouts.gymworkouts.Requests.RegisterUserRequest;
import com.gymworkouts.gymworkouts.Service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserRegistrationListener {
    private EmailService emailService;
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = {"q.user-registration"}, containerFactory = "registrationListenerContainerFactory")
    public void onUserRegistration(RegisterUserRequest event) {
        log.info("User Registration Event Received: {}", event);

        executeRegistration(event);

        rabbitTemplate.convertAndSend("x.post-registration","", event);
    }

    private void executeRegistration(RegisterUserRequest event) {
        log.info("Executing User Registration Event: {}", event);
        emailService.sendEmail(event.getEmail());
        throw new RuntimeException("Registration Failed");

    }
}
