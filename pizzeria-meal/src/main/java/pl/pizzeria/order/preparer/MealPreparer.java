package pl.pizzeria.order.preparer;

import pl.pizzeria.meal.domain.Meal;
import pl.pizzeria.meal.domain.MealDto;
import pl.pizzeria.order.domain.MealRequest;

import java.util.List;

public interface MealPreparer {
    MealDto prepare(Meal meal, MealRequest mealRequest, List<Meal> menu);
}
