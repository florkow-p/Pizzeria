package pl.pizzeria.meal.domain.dinner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.pizzeria.meal.domain.Meal;
import pl.pizzeria.meal.domain.MealType;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Entity
public class Extras extends Meal implements Serializable {
    private static final long serialVersionUID = -8911006038485271782L;

    @NotNull
    @Positive
    @Transient
    @JsonIgnore
    private Integer quantity;

    @Builder
    public Extras(Long id, @NotBlank String name, @NotEmpty MealType mealType, @Positive BigDecimal price) {
        super(id, name, mealType, price);
    }
}
