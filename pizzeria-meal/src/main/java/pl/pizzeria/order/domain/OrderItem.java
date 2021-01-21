package pl.pizzeria.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.pizzeria.meal.domain.Meal;

@Getter
@AllArgsConstructor
public class OrderItem {

    public static OrderItem from(Meal meal, Integer quantity) {
        return new OrderItem(meal, quantity);
    }

    private final Meal meal;
    private final Integer quantity;
}
