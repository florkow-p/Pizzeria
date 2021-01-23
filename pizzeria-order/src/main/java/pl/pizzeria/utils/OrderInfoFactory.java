package pl.pizzeria.utils;

import pl.pizzeria.order.domain.Order;
import pl.pizzeria.order.domain.OrderInfo;

public class OrderInfoFactory {
    public static OrderInfo from(Order order) {
        return OrderInfo.builder()
                .orderId(order.getId())
                .name(order.getOrderDetails().getName())
                .comment(order.getOrderDetails().getComment())
                .price(order.getPrice())
                .build();
    }
}
