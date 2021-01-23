package pl.pizzeria.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.pizzeria.meal.domain.MealDto;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1302395851962942556L;

    private MealDto meal;
    private Integer quantity;
}
