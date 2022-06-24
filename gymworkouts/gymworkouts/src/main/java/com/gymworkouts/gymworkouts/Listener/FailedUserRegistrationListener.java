package com.gymworkouts.gymworkouts.Listener;

import com.gymworkouts.gymworkouts.Requests.RegisterUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FailedUserRegistrationListener {
    @RabbitListener(queues = {"q.fall-back-registration"})
    public void onRegistrationFailure(RegisterUserRequest failedRegistration){
        log.info("Executing fallback for failed registration {}", failedRegistration);
    }
}
