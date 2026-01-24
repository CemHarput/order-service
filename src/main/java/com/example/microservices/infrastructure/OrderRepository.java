package com.example.microservices.infrastructure;

import com.example.microservices.domain.Order;
import com.example.microservices.domain.OrderId;
import io.smallrye.mutiny.Uni;

public interface OrderRepository {

    Uni<Void> save(Order order);

    Uni<Order> findById(OrderId id);
}
