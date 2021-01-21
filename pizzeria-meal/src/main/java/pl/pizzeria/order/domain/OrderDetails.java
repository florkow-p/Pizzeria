package pl.pizzeria.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderDetails {

    public static OrderDetails from(String name, String comment) {
        return new OrderDetails(name, comment);
    }

    private final String name;
    private final String comment;
}
