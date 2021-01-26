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
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MealDto implements Serializable {
    private static final long serialVersionUID = 4980879196961467064L;

    @NotBlank
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MealType mealType;

    @Positive
    @NotNull
    private BigDecimal price;

    public void addPrice(BigDecimal price) {
        this.price = this.price.add(price);
    }

    public BigDecimal getTotalPrice() {
        return getPrice();
    }
}
