package pl.pizzeria.meal.domain.pizza;

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
public class Topping extends Meal implements Serializable {
    private static final long serialVersionUID = -789106266171072293L;

    @NotNull
    @Positive
    @Transient
    @JsonIgnore
    private Integer quantity;
}
