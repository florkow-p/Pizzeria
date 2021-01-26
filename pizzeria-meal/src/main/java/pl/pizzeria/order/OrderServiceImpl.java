package pl.pizzeria.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pizzeria.meal.domain.Meal;
import pl.pizzeria.meal.domain.MealDto;
import pl.pizzeria.meal.domain.MealType;
import pl.pizzeria.meal.domain.dinner.Dinner;
import pl.pizzeria.meal.domain.mapper.MealMapper;
import pl.pizzeria.meal.domain.pizza.Pizza;
import pl.pizzeria.meal.web.MealServiceImpl;
import pl.pizzeria.order.domain.MealRequest;
import pl.pizzeria.order.domain.Order;
import pl.pizzeria.order.domain.OrderItem;
import pl.pizzeria.order.domain.OrderRequest;
import pl.pizzeria.order.preparer.DinnerPreparer;
import pl.pizzeria.order.preparer.PizzaPreparer;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MealServiceImpl mealService;
    private final PizzaPreparer pizzaPreparer;
    private final DinnerPreparer dinnerPreparer;

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
        BigDecimal totalOrderPrice = new BigDecimal(0);
        List<MealRequest> mealRequests = orderRequest.getMealRequests();
        init();

        for(MealRequest mealRequest: mealRequests) {
            Meal meal = mealService.findById(mealRequest.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid meal id"));

            if(!isMealValid(meal)) {
                throw new IllegalArgumentException("Invalid meal type");
            }

            MealDto mealDto;
            if(meal instanceof Pizza) {
                mealDto = pizzaPreparer.prepare(meal, mealRequest);
            } else if(meal instanceof Dinner) {
                mealDto = dinnerPreparer.prepare(meal, mealRequest);
            } else {
                mealDto = MealMapper.INSTANCE.mealToMealDto(meal);
            }

            totalOrderPrice = totalOrderPrice.add(mealDto.getTotalPrice());
            orderItems.add(OrderItem.from(mealDto, mealRequest.getQuantity()));
        }

        this.order = new Order(orderItems, orderRequest.getOrderDetails(), totalOrderPrice);

        return order;
    }

    private void init() {
        this.order = new Order();
        this.orderItems = new HashSet<>();
    }

    public boolean isMealValid(Meal meal) {
        List<MealType> validMealTypes = Arrays.asList(MealType.PIZZA, MealType.DINNER, MealType.DRINKS, MealType.SOUP);
        return validMealTypes.contains(meal.getMealType());
    }
}
