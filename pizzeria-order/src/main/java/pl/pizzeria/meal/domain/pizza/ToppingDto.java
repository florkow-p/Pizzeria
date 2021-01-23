package pl.pizzeria.meal.domain.pizza;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pizzeria.meal.domain.MealDto;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ToppingDto extends MealDto implements Serializable {
    private static final long serialVersionUID = -789106266171072293L;
}
