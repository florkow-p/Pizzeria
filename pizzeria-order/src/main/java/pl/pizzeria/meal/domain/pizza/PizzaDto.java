package pl.pizzeria.meal.domain.pizza;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pizzeria.meal.domain.MealDto;
import pl.pizzeria.meal.domain.MealType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PizzaDto extends MealDto implements Serializable {
    private static final long serialVersionUID = 6596146530547941213L;

    private List<ToppingDto> toppings = new LinkedList<>();

    @Builder
    public PizzaDto(@NotBlank String name, @NotNull MealType mealType,
                    @Positive BigDecimal price, List<ToppingDto> toppings) {
        super(name, mealType, price);
        this.toppings = toppings;
    }
}
