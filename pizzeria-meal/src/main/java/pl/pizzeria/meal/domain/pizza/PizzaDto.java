package pl.pizzeria.meal.domain.pizza;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pizzeria.meal.domain.MealDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PizzaDto extends MealDto implements Serializable {
    private static final long serialVersionUID = 6596146530547941213L;

    private List<ToppingDto> toppings;

    @Override
    public BigDecimal getTotalPrice() {
        if(toppings == null) {
            toppings = new LinkedList<>();

            return getPrice();
        }

        BigDecimal totalPrice = new BigDecimal(String.valueOf(getPrice()));
        for(ToppingDto topping: toppings) {
            totalPrice = totalPrice.add(topping.getPrice());
        }

        return totalPrice;
    }
}
