package pl.pizzeria.order.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pizzeria.order.domain.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Optional<Order> findById(UUID id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> get() {
        return orderRepository.findAll();
    }
}
