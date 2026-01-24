package com.example.microservices.domain;

import java.math.BigDecimal;
import java.util.Currency;

public record Money(BigDecimal amount, Currency currency) {
    private static final Currency USD = Currency.getInstance("USD");

    public Money {
        if (amount == null || amount.signum() < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    public static Money of(BigDecimal amount) {
        return new Money(amount, USD);
    }
}
