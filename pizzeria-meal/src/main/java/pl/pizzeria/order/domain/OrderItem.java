package pl.pizzeria.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.pizzeria.meal.domain.MealDto;

@Getter
@AllArgsConstructor
public class OrderItem {

    public static OrderItem from(MealDto mealDto, Integer quantity) {
        return new OrderItem(mealDto, quantity);
    }

    private final MealDto meal;
    private final Integer quantity;
}
