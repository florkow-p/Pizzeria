package pl.pizzeria.meal.domain.dinner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.pizzeria.meal.domain.Meal;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder
public class BaseIngredient extends Meal implements Serializable {
    private static final long serialVersionUID = 4303008782058727166L;

    @NotNull
    @Positive
    @Transient
    @JsonIgnore
    private Integer quantity;
}
