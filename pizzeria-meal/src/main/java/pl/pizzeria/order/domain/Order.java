package pl.pizzeria.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class Order {
    private final Set<OrderItem> orderItemSet;
    private final OrderDetails orderDetails;
}
