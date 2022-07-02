package com.gymworkouts.gymworkouts.Listeners;

import com.gymworkouts.gymworkouts.Service.RpcService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class RegisterListener {

    @Autowired
    private RpcService rpcService;

    @RabbitListener(queues = "register-queue")
    public void handleRegisterQueue(
            @Header(value = AmqpHeaders.REPLY_TO, required = false) String senderId,
            @Header(value = AmqpHeaders.CORRELATION_ID, required = false) String correlationId,
            String email
    ) {
        System.out.println("Register user with email: " + email);
        if (senderId != null && correlationId != null) {
            String responseMessage = "Successfully registered user with email: " + email;
            this.rpcService.sendResponse(senderId, correlationId, responseMessage);
        }
    }
}
