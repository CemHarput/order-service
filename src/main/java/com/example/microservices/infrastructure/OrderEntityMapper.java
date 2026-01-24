package com.example.microservices.infrastructure;

import com.example.microservices.domain.Money;
import com.example.microservices.domain.Order;
import com.example.microservices.domain.OrderId;
import com.example.microservices.domain.UserId;

public final class OrderEntityMapper {

    private OrderEntityMapper() {}

    public static OrderEntity toEntity(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.id = order.getId().value();
        entity.userId = order.getUserId().value();
        entity.totalPrice = order.getTotalPrice().amount();
        entity.status = order.getStatus();
        entity.createdAt = order.getCreatedAt();
        return entity;
    }

    public static Order toDomain(OrderEntity entity) {
        return Order.rehydrate(
                new OrderId(entity.id),
                new UserId(entity.userId),
                Money.of(entity.totalPrice),
                entity.status,
                entity.createdAt
        );
    }
}