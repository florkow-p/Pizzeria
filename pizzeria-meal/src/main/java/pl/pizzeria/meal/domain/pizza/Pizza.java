package pl.pizzeria.meal.domain.pizza;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.pizzeria.meal.domain.Meal;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder
public class Pizza extends Meal {
    @Transient
    @JsonIgnore
    private List<Topping> toppings;

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
