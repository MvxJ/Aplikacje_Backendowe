package com.mailer.mailer.Listener;

import com.mailer.mailer.Service.MailerService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
public class RegisterListener {

    @Autowired
    private MailerService mailerService;

    @RabbitListener(queues = "${gumworkouts.rabbitmq.queue}")
    public void handleRegisterQueue(
            String email
    ) {
        System.out.println("Register user with email: " + email);

        //TODO:: Please insert smtp data
        mailerService.setFrom("*****");
        mailerService.setHost("*****");
        mailerService.setPassword("*****");
        mailerService.setTo(email);
        mailerService.send(
                "User registration confirmation",
                "Your account was successfully created!"
        );
    }
}
