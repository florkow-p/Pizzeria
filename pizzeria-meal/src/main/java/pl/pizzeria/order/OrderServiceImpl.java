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

    public static final String ORDER_NOT_PREPARED = "Order was not prepared";
    public static final String INVALID_MEAL_TYPE = "Invalid meal type";
    public static final String INVALID_MEAL_ID = "Invalid meal id";

    private final MealServiceImpl mealService;
    private final PizzaPreparer pizzaPreparer;
    private final DinnerPreparer dinnerPreparer;

    private Order order;
    private List<Meal> menu;
    private Set<OrderItem> orderItems;

    private void init() {
        this.order = new Order();
        this.orderItems = new HashSet<>();
        this.menu = mealService.findAll();
    }

    @Override
    public Order getOrder() {
        if(order != null) {
            return order;
        }

        throw new IllegalStateException(ORDER_NOT_PREPARED);
    }

    @Override
    public Order prepareOrder(OrderRequest orderRequest) {
        List<MealRequest> mealRequests = orderRequest.getMealRequests();
        BigDecimal totalOrderPrice = new BigDecimal(0);
        init();

        MealDto mealDto;
        for(MealRequest mealRequest: mealRequests) {
            Meal meal = menu.stream()
                    .filter(item -> item.getId().equals(mealRequest.getId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(INVALID_MEAL_ID));

            if(!isMealValid(meal)) {
                throw new IllegalArgumentException(INVALID_MEAL_TYPE);
            }

            mealDto = prepareMeal(meal, mealRequest, menu);

            totalOrderPrice = totalOrderPrice.add(calculateTotalPrice(mealDto, mealRequest));
            orderItems.add(OrderItem.from(mealDto, mealRequest.getQuantity()));
        }

        this.order = new Order(orderItems, orderRequest.getOrderDetails(), totalOrderPrice);

        return order;
    }

    private MealDto prepareMeal(Meal meal, MealRequest mealRequest, List<Meal> menu) {
        MealDto mealDto;
        if(meal instanceof Pizza) {
            mealDto = pizzaPreparer.prepare(meal, mealRequest, menu);
        } else if(meal instanceof Dinner) {
            mealDto = dinnerPreparer.prepare(meal, mealRequest, menu);
        } else {
            mealDto = MealMapper.INSTANCE.mealToMealDto(meal);
        }

        return mealDto;
    }

    private BigDecimal calculateTotalPrice(MealDto mealDto, MealRequest mealRequest) {
        return mealDto.getTotalPrice().multiply(BigDecimal.valueOf(mealRequest.getQuantity()));
    }

    private boolean isMealValid(Meal meal) {
        List<MealType> validMealTypes = Arrays.asList(MealType.PIZZA, MealType.DINNER, MealType.DRINKS, MealType.SOUP);
        return validMealTypes.contains(meal.getMealType());
    }
}
