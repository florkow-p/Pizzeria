package pl.pizzeria.meal.domain.dinner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pizzeria.meal.domain.MealDto;
import pl.pizzeria.meal.domain.MealType;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class DinnerDto extends MealDto {
    @Transient
    private Extras extras;
    @Transient
    private BaseIngredient baseIngredient;
    @JsonIgnore
    private boolean allowedBaseIngredient;

    @Builder
    public DinnerDto(@NotBlank String name, @NotNull MealType mealType, @Positive BigDecimal price,
                  Extras extras, BaseIngredient baseIngredient, boolean allowedBaseIngredient) {
        super(name, mealType, price);
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
        if (!(o instanceof DinnerDto)) return false;
        if (!super.equals(o)) return false;
        DinnerDto dinnerDto = (DinnerDto) o;
        return allowedBaseIngredient == dinnerDto.allowedBaseIngredient
                && Objects.equals(extras, dinnerDto.extras)
                && Objects.equals(baseIngredient, dinnerDto.baseIngredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), extras, baseIngredient, allowedBaseIngredient);
    }
}
