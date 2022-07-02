package com.gymworkouts.gymworkouts.Configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class ConsumerConfiguration {

    @Bean
    public Queue registerConsumerQueue() {
        return new Queue("register-queue");
    }
}
