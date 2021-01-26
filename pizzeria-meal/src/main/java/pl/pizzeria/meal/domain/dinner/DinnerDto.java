package pl.pizzeria.meal.domain.dinner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pizzeria.meal.domain.MealDto;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class DinnerDto extends MealDto implements Serializable {
    private static final long serialVersionUID = 5855353372961867540L;

    private ExtrasDto extras;

    private BaseIngredientDto baseIngredient;

    @JsonIgnore
    private boolean allowedBaseIngredient;

    @Override
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(String.valueOf(getPrice()));

        if(extras != null) {
            totalPrice = totalPrice.add(extras.getPrice());
        }
        if(baseIngredient != null) {
            totalPrice = totalPrice.add(baseIngredient.getPrice());
        }

        return totalPrice;
    }
}
