package com.inmobiliaria.server.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HashUtility {

    public static String generateHash(Object object) {
        
        try {
            
            String data = new ObjectMapper().writeValueAsString(object);

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));

            StringBuilder hashString = new StringBuilder();
            for (byte b : hashBytes) {
                hashString.append(String.format("%02x", b));
            }
            return hashString.toString();

        } catch (Exception e) {
            throw new RuntimeException("Error generating hash", e);
        }
    }
}

