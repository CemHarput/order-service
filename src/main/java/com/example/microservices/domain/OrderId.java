package com.example.microservices.domain;

import java.util.UUID;

public record OrderId(UUID value) {

    public static OrderId newId() {
        return new OrderId(UUID.randomUUID());
    }
}
