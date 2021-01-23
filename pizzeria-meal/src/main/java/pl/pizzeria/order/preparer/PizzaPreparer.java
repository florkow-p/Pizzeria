package pl.pizzeria.order.preparer;

import lombok.RequiredArgsConstructor;
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
import pl.pizzeria.meal.web.MealServiceImpl;
import pl.pizzeria.order.domain.MealRequest;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PizzaPreparer implements MealPreparer {

    private final MealServiceImpl mealService;

    @Override
    public MealDto prepare(Meal meal, MealRequest mealRequest) {
        MealDto mealDto = PizzaMapper.INSTANCE.pizzaToPizzaDto((Pizza) meal);
        preparePizza(mealDto, mealRequest);
        return mealDto;
    }

    private void preparePizza(MealDto mealDto, MealRequest mealRequest) {
        if(hasTopping(mealRequest)) {
            List<Meal> toppings = mealService.findByIdIn(mealRequest.getToppings());
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
                throw new IllegalArgumentException("Invalid topping id");
            }
        }

        ((PizzaDto) mealDto).setToppings(toppingDtoList);
    }
}
