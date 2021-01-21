package pl.pizzeria.meal.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pizzeria.meal.domain.Meal;
import pl.pizzeria.order.OrderServiceImpl;
import pl.pizzeria.order.domain.Order;
import pl.pizzeria.order.domain.OrderRequest;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
public class MealController {

    private final MealServiceImpl mealService;
    private final OrderServiceImpl orderService;

    @GetMapping("")
    public ResponseEntity<List<Meal>> getMenu() {
        List<Meal> menu = mealService.findAll();

        if(menu == null || menu.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<Order> postOrder(@RequestBody OrderRequest orderRequest) {
        Order order = orderService.prepareOrder(orderRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
