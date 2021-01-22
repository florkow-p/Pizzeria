package pl.pizzeria.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Set<OrderItem> orderItemSet;
    private OrderDetails orderDetails;
    private BigDecimal price;
}
