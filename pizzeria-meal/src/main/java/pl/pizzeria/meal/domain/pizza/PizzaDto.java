package pl.pizzeria.meal.domain.pizza;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pizzeria.meal.domain.MealDto;
import pl.pizzeria.meal.domain.MealType;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class PizzaDto extends MealDto {
    @Transient
    private List<Topping> toppings;

    @Builder
    public PizzaDto(@NotBlank String name, @NotNull MealType mealType,
                 @Positive BigDecimal price, List<Topping> toppings) {
        super(name, mealType, price);
        this.toppings = toppings;
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PizzaDto)) return false;
        if (!super.equals(o)) return false;
        PizzaDto pizzaDto = (PizzaDto) o;
        return Objects.equals(toppings, pizzaDto.toppings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), toppings);
    }
}
