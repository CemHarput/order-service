package com.example.microservices.mapper;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateOrderRequest(UUID userId,BigDecimal totalPrice) {

}
