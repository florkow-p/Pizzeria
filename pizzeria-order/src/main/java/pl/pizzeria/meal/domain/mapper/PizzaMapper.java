package pl.pizzeria.meal.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.pizzeria.meal.domain.pizza.Pizza;
import pl.pizzeria.meal.domain.pizza.PizzaDto;

@Mapper
public interface PizzaMapper {
    PizzaMapper INSTANCE = Mappers.getMapper(PizzaMapper.class);

    Pizza pizzaDtoToPizza(PizzaDto pizzaDto);
    PizzaDto pizzaToPizzaDto(Pizza Pizza);
}
