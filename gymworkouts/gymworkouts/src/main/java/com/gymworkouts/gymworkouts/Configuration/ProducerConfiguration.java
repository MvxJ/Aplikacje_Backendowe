package com.gymworkouts.gymworkouts.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class ProducerConfiguration {

    @Bean
    public Queue registerProducerQueue() {
        return new Queue("register-queue");
    }
}
