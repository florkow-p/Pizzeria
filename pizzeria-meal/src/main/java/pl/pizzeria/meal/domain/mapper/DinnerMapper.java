package pl.pizzeria.meal.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.pizzeria.meal.domain.dinner.Dinner;
import pl.pizzeria.meal.domain.dinner.DinnerDto;

@Mapper
public interface DinnerMapper {
    DinnerMapper INSTANCE = Mappers.getMapper(DinnerMapper.class);

    Dinner dinnerDtoToDinner(DinnerDto dinnerDto);
    DinnerDto dinnerToDinnerDto(Dinner dinner);
}
