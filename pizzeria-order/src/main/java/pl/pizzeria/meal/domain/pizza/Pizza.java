package pl.pizzeria.meal.domain.pizza;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.pizzeria.meal.domain.Meal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
public class Pizza extends Meal {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pizza_id")
    private List<Topping> toppings;

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
