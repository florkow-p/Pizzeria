package pl.pizzeria.meal.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MealDto {
    @NotBlank
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MealType mealType;

    @Positive
    @NotNull
    private BigDecimal price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealDto mealDto = (MealDto) o;
        return  Objects.equals(name, mealDto.name)
                && mealType == mealDto.mealType
                && Objects.equals(price, mealDto.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mealType, price);
    }
}
