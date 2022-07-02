package com.gymworkouts.gymworkouts.Service;

import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Base64.Decoder;

@Service
public class PasswordService {
    public String encodePassword(String password) {
        Encoder encoder = Base64.getEncoder();
        String encodedString = encoder.encodeToString(password.getBytes());

        return encodedString;
    }

    public String decodePassword(String encodedString) {
        Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(encodedString);

        return new String(bytes);
    }
}
