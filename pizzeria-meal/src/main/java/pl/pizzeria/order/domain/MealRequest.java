package pl.pizzeria.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MealRequest {
    @NotNull
    @Positive
    private Long id;

    @NotNull
    @Positive
    private Integer quantity;

    private List<Long> toppings;
    private Long extrasId;
    private Long baseIngredientId;
}
