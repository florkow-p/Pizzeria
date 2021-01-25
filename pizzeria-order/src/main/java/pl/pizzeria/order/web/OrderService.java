package pl.pizzeria.order.web;

import pl.pizzeria.order.domain.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    Optional<Order> findById(UUID id);
    Order save(Order order);
}
