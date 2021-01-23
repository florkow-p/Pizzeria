package pl.pizzeria.meal.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.pizzeria.meal.domain.pizza.Topping;
import pl.pizzeria.meal.domain.pizza.ToppingDto;

@Mapper
public interface ToppingMapper {
    ToppingMapper INSTANCE = Mappers.getMapper(ToppingMapper.class);

    Topping toppingDtoToTopping(ToppingDto toppingDto);
    ToppingDto toppingToToppingDto(Topping topping);
}
