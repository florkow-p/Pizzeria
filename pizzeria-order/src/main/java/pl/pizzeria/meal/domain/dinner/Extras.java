package pl.pizzeria.meal.domain.dinner;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.pizzeria.meal.domain.Meal;

import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
public class Extras extends Meal implements Serializable {
    private static final long serialVersionUID = -8911006038485271782L;
}
