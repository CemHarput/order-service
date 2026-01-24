package com.example.microservices.domain;


import java.util.UUID;

public record UserId(UUID value) {
    public UserId {
        if (value == null) {
            throw new IllegalArgumentException("UserId cannot be empty");
        }
    }
}
