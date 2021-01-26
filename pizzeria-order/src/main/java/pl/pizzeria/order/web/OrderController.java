package pl.pizzeria.order.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pizzeria.order.domain.Order;

import java.util.List;
import java.util.UUID;

@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/history")
public class OrderController {

    private final OrderServiceImpl orderService;

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getOrdersHistory() {
        return new ResponseEntity<>(orderService.get(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Order> getOrderById(@RequestParam final String id) {
        return orderService.findById(UUID.fromString(id))
                .map(order -> new ResponseEntity<>(order, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleException(IllegalArgumentException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
