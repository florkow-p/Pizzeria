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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
public class Dinner extends Meal {
    @Transient
    @JsonIgnore
    private Extras extras;

    @Transient
    @JsonIgnore
    private BaseIngredient baseIngredient;

    private boolean allowedBaseIngredient;

    @Builder
    public Dinner(Long id, @NotBlank String name, @NotNull MealType mealType, @Positive BigDecimal price,
                  Extras extras, BaseIngredient baseIngredient, boolean allowedBaseIngredient) {
        super(id, name, mealType, price);
        this.extras = extras;
        this.baseIngredient = baseIngredient;
        this.allowedBaseIngredient = allowedBaseIngredient;
    }

    public void setBaseIngredient(BaseIngredient baseIngredient) {
        this.baseIngredient = baseIngredient;
    }

    public void setExtras(Extras extras) {
        this.extras = extras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dinner)) return false;
        if (!super.equals(o)) return false;
        Dinner dinner = (Dinner) o;
        return allowedBaseIngredient == dinner.allowedBaseIngredient
                && Objects.equals(extras, dinner.extras)
                && Objects.equals(baseIngredient, dinner.baseIngredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), extras, baseIngredient, allowedBaseIngredient);
    }
}
