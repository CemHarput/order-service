package com.example.microservices.infrastructure;

import com.example.microservices.domain.Order;
import com.example.microservices.domain.OrderId;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderRepositoryImpl implements OrderRepository{
    @Override
    public Uni<Void> save(Order order) {
        return OrderEntity
                .persist(OrderEntityMapper.toEntity(order))
                .replaceWithVoid();
    }


    @Override
    public Uni<Order> findById(OrderId id) {
        return OrderEntity.<OrderEntity>findById(id.value())
                .map(OrderEntityMapper::toDomain);
    }
}
