package pl.pizzeria.meal.domain.dinner;

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
public class BaseIngredient extends Meal implements Serializable {
    private static final long serialVersionUID = 4303008782058727166L;
}
