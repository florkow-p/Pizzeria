package pl.pizzeria.order.web;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pizzeria.order.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
