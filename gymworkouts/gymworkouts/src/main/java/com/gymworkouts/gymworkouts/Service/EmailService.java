package com.gymworkouts.gymworkouts.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {
    public void sendEmail(String email) {
        log.info("Sending email to {}", email);
    }
}
