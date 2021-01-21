package pl.pizzeria.meal.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MealExceptionHandler {

    @ExceptionHandler(value = {
            TransactionSystemException.class,
            IllegalArgumentException.class,
            IllegalStateException.class})
    public ResponseEntity<?> handleExceptions(Exception e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
