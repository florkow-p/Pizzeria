package pl.pizzeria.meal.domain.pizza;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.pizzeria.meal.domain.Meal;

import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
public class Topping extends Meal implements Serializable {
    private static final long serialVersionUID = -789106266171072293L;
}
