package pl.pizzeria.meal.domain.dinner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.pizzeria.meal.domain.Meal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.util.Objects;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
public class Dinner extends Meal {

    @OneToOne(cascade = CascadeType.ALL)
    private Extras extras;

    @OneToOne(cascade = CascadeType.ALL)
    private BaseIngredient baseIngredient;

    @Transient
    @JsonIgnore
    private boolean allowedBaseIngredient;

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
