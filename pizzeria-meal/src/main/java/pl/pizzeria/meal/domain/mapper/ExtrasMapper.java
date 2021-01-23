package pl.pizzeria.meal.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.pizzeria.meal.domain.dinner.Extras;
import pl.pizzeria.meal.domain.dinner.ExtrasDto;


@Mapper
public interface ExtrasMapper {
    ExtrasMapper INSTANCE = Mappers.getMapper(ExtrasMapper.class);

    Extras extrasDtoToExtras(ExtrasDto extrasDto);
    ExtrasDto extrasToExtrasDto(Extras Extras);
}
