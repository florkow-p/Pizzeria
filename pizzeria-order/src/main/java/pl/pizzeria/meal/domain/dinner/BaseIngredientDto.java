package pl.pizzeria.meal.domain.dinner;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pizzeria.meal.domain.MealDto;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class BaseIngredientDto extends MealDto implements Serializable {
    private static final long serialVersionUID = 3090941580173535223L;
}
