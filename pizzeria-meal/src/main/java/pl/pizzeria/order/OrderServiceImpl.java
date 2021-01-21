package pl.pizzeria.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pizzeria.meal.domain.Meal;
import pl.pizzeria.meal.domain.MealDto;
import pl.pizzeria.meal.domain.MealType;
import pl.pizzeria.meal.domain.dinner.BaseIngredient;
import pl.pizzeria.meal.domain.dinner.Dinner;
import pl.pizzeria.meal.domain.dinner.DinnerDto;
import pl.pizzeria.meal.domain.dinner.Extras;
import pl.pizzeria.meal.domain.mapper.DinnerMapper;
import pl.pizzeria.meal.domain.mapper.MealMapper;
import pl.pizzeria.meal.domain.mapper.PizzaMapper;
import pl.pizzeria.meal.domain.pizza.Pizza;
import pl.pizzeria.meal.domain.pizza.PizzaDto;
import pl.pizzeria.meal.domain.pizza.Topping;
import pl.pizzeria.meal.web.MealServiceImpl;
import pl.pizzeria.order.domain.MealRequest;
import pl.pizzeria.order.domain.Order;
import pl.pizzeria.order.domain.OrderItem;
import pl.pizzeria.order.domain.OrderRequest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MealServiceImpl mealService;
    private Order order;
    private Set<OrderItem> orderItems;

    @Override
    public Order getOrder() {
        if(order != null) {
            return order;
        }

        throw new IllegalStateException("Order was not prepared");
    }

    @Override
    public Order prepareOrder(OrderRequest orderRequest) {
        List<MealRequest> mealRequests = orderRequest.getMealRequests();

        for(MealRequest mealRequest: mealRequests) {
            Meal meal = mealService.findById(mealRequest.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid meal id"));

            if(!isMealValid(meal)) {
                throw new IllegalArgumentException("Invalid meal type");
            }

            MealDto mealDto;
            if(meal instanceof Pizza) {
                mealDto = PizzaMapper.INSTANCE.pizzaToPizzaDto((Pizza) meal);
                preparePizza(mealDto, mealRequest);
            } else if(meal instanceof Dinner) {
                mealDto = DinnerMapper.INSTANCE.dinnerToDinnerDto((Dinner) meal);
                prepareDinner(mealDto, mealRequest);
            } else {
                mealDto = MealMapper.INSTANCE.mealToMealDto(meal);
            }

            addOrderItem(OrderItem.from(MealMapper.INSTANCE.mealDtoToMeal(mealDto), mealRequest.getQuantity()));
        }

        this.order = new Order(orderItems, orderRequest.getOrderDetails());

        return order;
    }

    public boolean isMealValid(Meal meal) {
        List<MealType> validMealTypes = Arrays.asList(MealType.PIZZA, MealType.DINNER, MealType.DRINKS, MealType.SOUP);
        return validMealTypes.contains(meal.getMealType());
    }

    private void addOrderItem(OrderItem orderItem) {
        if(this.orderItems == null) {
            this.orderItems = new HashSet<>();
        }

        orderItems.add(orderItem);
    }

    private void prepareDinner(MealDto mealDto, MealRequest mealRequest) {
        if(isBaseIngredientAllowed(mealDto, mealRequest)) {
            addBaseIngredient(mealDto, mealRequest);
        }

        if (mealRequest.getExtrasId() != null) {
            addExtras(mealDto, mealRequest);
        }
    }

    private boolean isBaseIngredientAllowed(MealDto mealDto, MealRequest mealRequest) {
        return ((DinnerDto) mealDto).isAllowedBaseIngredient() && mealRequest.getBaseIngredientId() != null;
    }

    private void addBaseIngredient(MealDto mealDto, MealRequest mealRequest) {
        Meal ingredient = mealService.findById(mealRequest.getBaseIngredientId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid base ingredient id"));

        ((DinnerDto) mealDto).setBaseIngredient((BaseIngredient) ingredient);
    }

    private void addExtras(MealDto mealDto, MealRequest mealRequest) {
        Meal extras = mealService.findById(mealRequest.getExtrasId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid extras id"));

        ((DinnerDto) mealDto).setExtras((Extras) extras);
    }

    private void preparePizza(MealDto mealDto, MealRequest mealRequest) {
        if(mealRequest.getToppings() != null && !mealRequest.getToppings().isEmpty()) {
            List<Meal> toppings = mealService.findByIdIn(mealRequest.getToppings());
            addToppingsToPizza(mealDto, toppings);
        }
    }

    private void addToppingsToPizza(MealDto mealDto, List<Meal> toppings) {
        for(Meal topping: toppings) {
            if(MealType.TOPPING.equals(topping.getMealType())) {
                ((PizzaDto) mealDto).addTopping((Topping) topping);
            } else {
                throw new IllegalArgumentException("Invalid topping id");
            }
        }
    }
}
