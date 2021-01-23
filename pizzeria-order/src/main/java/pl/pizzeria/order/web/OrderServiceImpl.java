package pl.pizzeria.order.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pizzeria.order.domain.Order;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Optional<Order> findById(Long id) {
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
