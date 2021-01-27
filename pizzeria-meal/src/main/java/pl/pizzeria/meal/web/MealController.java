package pl.pizzeria.meal.web;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pizzeria.meal.domain.Meal;
import pl.pizzeria.order.OrderServiceImpl;
import pl.pizzeria.order.domain.Order;
import pl.pizzeria.order.domain.OrderRequest;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/v1/menu")
@RequiredArgsConstructor
public class MealController {

    @Value("${rabbit.save-order-queue}")
    private String saveOrderQueue;

    private final MealServiceImpl mealService;
    private final OrderServiceImpl orderService;
    private final RabbitTemplate rabbitTemplate;

    @GetMapping("")
    public ResponseEntity<List<Meal>> getMenu() {
        List<Meal> menu = mealService.findAll();

        if(menu == null || menu.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.GONE);
        }

        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Order> postOrder(@RequestBody OrderRequest orderRequest) {
        Order order = orderService.prepareOrder(orderRequest);

        rabbitTemplate.convertAndSend(saveOrderQueue, order);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
