package pl.pizzeria.order.preparer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.pizzeria.meal.domain.Meal;
import pl.pizzeria.meal.domain.MealDto;
import pl.pizzeria.meal.domain.MealType;
import pl.pizzeria.meal.domain.pizza.Pizza;
import pl.pizzeria.meal.domain.pizza.PizzaDto;
import pl.pizzeria.meal.domain.pizza.Topping;
import pl.pizzeria.meal.domain.pizza.ToppingDto;
import pl.pizzeria.meal.web.MealServiceImpl;
import pl.pizzeria.order.domain.MealRequest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class PizzaPreparerTest {

    @MockBean
    private MealServiceImpl mealService;

    @Autowired
    private PizzaPreparer pizzaPreparer;

    private Meal pizza;
    private List<Meal> toppings;
    private MealRequest pizzaMealRequest;

    @Test
    public void prepareValidPizza() {
        pizzaInit();
        given(mealService.findByIdIn(any())).willReturn(toppings);

        MealDto mealDto = pizzaPreparer.prepare(pizza, pizzaMealRequest);
        ToppingDto toppingDto = ((PizzaDto) mealDto).getToppings().get(0);

        assertEquals(mealDto.getTotalPrice(), BigDecimal.valueOf(12));
        assertEquals(mealDto.getName(), "name");
        assertEquals(mealDto.getMealType(), MealType.PIZZA);
        assertFalse(((PizzaDto) mealDto).getToppings().isEmpty());
        assertEquals(toppingDto.getName(), "topping name");
        assertEquals(toppingDto.getMealType(), MealType.TOPPING);
        Assertions.assertEquals(toppingDto.getTotalPrice(), BigDecimal.valueOf(2));
    }

    @Test
    public void invalidTopping_ShouldThrowException() {
        invalidPizzaInit();
        given(mealService.findByIdIn(any())).willReturn(toppings);

        assertThrows(IllegalArgumentException.class, () -> pizzaPreparer.prepare(pizza, pizzaMealRequest));
    }

    private void invalidPizzaInit() {
        buildValidPizza();
        buildPizzaMealRequest(getInvalidTopping());
    }

    private void pizzaInit() {
        buildValidPizza();
        buildPizzaMealRequest(getValidTopping());
    }

    private Topping getValidTopping() {
        return Topping.builder()
                .id(2L)
                .name("topping name")
                .price(BigDecimal.valueOf(2))
                .mealType(MealType.TOPPING)
                .build();
    }

    private Topping getInvalidTopping() {
        return Topping.builder()
                .id(2L)
                .name("topping name")
                .price(BigDecimal.valueOf(2))
                .mealType(MealType.DINNER)
                .build();
    }

    private void buildValidPizza() {
        this.pizza = Pizza.builder()
                .id(1L)
                .name("name")
                .price(BigDecimal.valueOf(10))
                .mealType(MealType.PIZZA)
                .build();
    }

    private void buildPizzaMealRequest(Topping topping) {
        toppings = List.of(topping);
        List<Long> toppingIds = List.of(topping.getId());
        this.pizzaMealRequest = new MealRequest(1L, 2, toppingIds, 3L, 4L);
    }
}