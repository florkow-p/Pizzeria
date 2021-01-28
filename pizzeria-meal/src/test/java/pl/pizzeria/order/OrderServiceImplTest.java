package pl.pizzeria.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.pizzeria.meal.domain.MealType;
import pl.pizzeria.order.domain.MealRequest;
import pl.pizzeria.order.domain.Order;
import pl.pizzeria.order.domain.OrderDetails;
import pl.pizzeria.order.domain.OrderRequest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private OrderRequest orderRequest;

    @Test
    public void orderNotPrepared_ShouldThrowException() {
        assertThrows(IllegalStateException.class, orderService::getOrder);
    }

    @Test
    public void shouldReturnValidOrder() {
        buildValidOrderRequest();

        Order order = orderService.prepareOrder(orderRequest);

        order.getOrderItemSet().forEach(orderItem -> assertEquals(orderItem.getMeal().getMealType(), MealType.PIZZA));
        assertEquals(order.getPrice().stripTrailingZeros(), BigDecimal.valueOf(66).stripTrailingZeros());
        // itd...
    }

    public void buildValidOrderRequest() {
        OrderDetails orderDetails = new OrderDetails("name", "comment");
        List<MealRequest> mealRequests = List.of(
                new MealRequest(1L, 2, List.of(), null, null),
                new MealRequest(2L, 1, List.of(5L, 6L), null, null)
        );

        this.orderRequest = new OrderRequest(orderDetails, mealRequests);
    }

}