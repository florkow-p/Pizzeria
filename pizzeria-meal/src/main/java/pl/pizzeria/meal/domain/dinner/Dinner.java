package pl.pizzeria.meal.domain.dinner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.pizzeria.meal.domain.Meal;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder
public class Dinner extends Meal {
    @Transient
    @JsonIgnore
    private Extras extras;

    @Transient
    @JsonIgnore
    private BaseIngredient baseIngredient;

    private boolean allowedBaseIngredient;

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
