package com.example.microservices.service;


import com.example.microservices.domain.Money;
import com.example.microservices.domain.Order;
import com.example.microservices.domain.OrderId;
import com.example.microservices.domain.UserId;
import com.example.microservices.infrastructure.OrderRepository;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderApplicationService {



    private final OrderRepository orderRepository;

    public OrderApplicationService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Uni<OrderId> createOrder(UserId userId, Money totalPrice){
        return Panache.withTransaction(()->{
           Order order = Order.create(userId,totalPrice);

           return orderRepository.save(order).replaceWith(order.getId());
        });
    }
    public Uni<Void> markOrderPaid(OrderId orderId) {
        return Panache.withTransaction(() ->
                orderRepository.findById(orderId)
                        .invoke(Order::markPaid)
                        .call(orderRepository::save)
        ).replaceWithVoid();
    }
}
