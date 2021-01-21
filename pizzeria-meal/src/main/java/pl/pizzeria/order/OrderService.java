package pl.pizzeria.order;

import pl.pizzeria.order.domain.Order;
import pl.pizzeria.order.domain.OrderRequest;

public interface OrderService {
    Order getOrder();
    Order prepareOrder(OrderRequest orderRequest);
}
