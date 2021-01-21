package pl.pizzeria.meal.domain.pizza;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.pizzeria.meal.domain.Meal;
import pl.pizzeria.meal.domain.MealType;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
public class Pizza extends Meal {
    @Transient
    @JsonIgnore
    private List<Topping> toppings;

    @Builder
    public Pizza(Long id, @NotBlank String name, @NotNull MealType mealType,
                 @Positive BigDecimal price, List<Topping> toppings) {
        super(id, name, mealType, price);
        this.toppings = toppings;
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pizza)) return false;
        if (!super.equals(o)) return false;
        Pizza pizza = (Pizza) o;
        return Objects.equals(toppings, pizza.toppings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), toppings);
    }
}
