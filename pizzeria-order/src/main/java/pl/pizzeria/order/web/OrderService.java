package pl.pizzeria.order.web;

import pl.pizzeria.order.domain.Order;

import java.util.Optional;

public interface OrderService {
    Optional<Order> findById(Long id);
    Order save(Order order);
}
