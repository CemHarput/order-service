package com.example.microservices.domain;

import java.time.Instant;

public class Order {


    private final OrderId id;
    private final UserId userId;
    private final Money totalPrice;
    private OrderStatus status;
    private final Instant createdAt;

    private Order(OrderId id,
                  UserId userId,
                  Money totalPrice,
                  OrderStatus status,
                  Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static Order create(UserId userId, Money totalPrice) {
        return new Order(
                OrderId.newId(),
                userId,
                totalPrice,
                OrderStatus.CREATED,
                Instant.now()
        );
    }

    public void markPaid() {
        if (status != OrderStatus.CREATED) {
            throw new IllegalStateException("Order cannot be paid from " + status);
        }
        this.status = OrderStatus.PAID;
    }

    public void cancel() {
        if (status == OrderStatus.PAID) {
            throw new IllegalStateException("Paid order cannot be cancelled");
        }
        this.status = OrderStatus.CANCELLED;
    }

    public OrderId getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public UserId getUserId() {
        return userId;
    }
}
