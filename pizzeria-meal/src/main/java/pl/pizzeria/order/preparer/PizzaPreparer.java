package pl.pizzeria.order.preparer;

import org.springframework.stereotype.Service;
import pl.pizzeria.meal.domain.Meal;
import pl.pizzeria.meal.domain.MealDto;
import pl.pizzeria.meal.domain.MealType;
import pl.pizzeria.meal.domain.mapper.PizzaMapper;
import pl.pizzeria.meal.domain.mapper.ToppingMapper;
import pl.pizzeria.meal.domain.pizza.Pizza;
import pl.pizzeria.meal.domain.pizza.PizzaDto;
import pl.pizzeria.meal.domain.pizza.Topping;
import pl.pizzeria.meal.domain.pizza.ToppingDto;
import pl.pizzeria.order.domain.MealRequest;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaPreparer implements MealPreparer {

    public static final String INVALID_TOPPING_ID = "Invalid topping id";

    @Override
    public MealDto prepare(Meal meal, MealRequest mealRequest, List<Meal> menu) {
        MealDto mealDto = PizzaMapper.INSTANCE.pizzaToPizzaDto((Pizza) meal);
        preparePizza(mealDto, mealRequest, menu);
        return mealDto;
    }

    private void preparePizza(MealDto mealDto, MealRequest mealRequest, List<Meal> menu) {
        if(hasTopping(mealRequest)) {
            List<Meal> toppings = menu.stream()
                    .filter(item -> mealRequest.getToppings().contains(item.getId()))
                    .collect(Collectors.toList());
            addToppingsToPizza(mealDto, toppings);
        }
    }

    private boolean hasTopping(MealRequest mealRequest) {
        return mealRequest.getToppings() != null && !mealRequest.getToppings().isEmpty();
    }

    private void addToppingsToPizza(MealDto mealDto, List<Meal> toppings) {
        List<ToppingDto> toppingDtoList = new LinkedList<>();
        ToppingDto toppingDto;

        for(Meal topping: toppings) {
            toppingDto = ToppingMapper.INSTANCE.toppingToToppingDto((Topping) topping);

            if(MealType.TOPPING.equals(topping.getMealType())) {
                toppingDtoList.add(toppingDto);
            } else {
                throw new IllegalArgumentException(INVALID_TOPPING_ID);
            }
        }

        ((PizzaDto) mealDto).setToppings(toppingDtoList);
    }
}
