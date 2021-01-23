package pl.pizzeria.meal.domain.dinner;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pizzeria.meal.domain.MealDto;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ExtrasDto extends MealDto implements Serializable {
    private static final long serialVersionUID = 898089790690164668L;
}
