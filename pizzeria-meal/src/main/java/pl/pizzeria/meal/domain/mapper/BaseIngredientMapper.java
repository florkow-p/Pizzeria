package pl.pizzeria.meal.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.pizzeria.meal.domain.dinner.BaseIngredient;
import pl.pizzeria.meal.domain.dinner.BaseIngredientDto;

@Mapper
public interface BaseIngredientMapper {
    BaseIngredientMapper INSTANCE = Mappers.getMapper(BaseIngredientMapper.class);

    BaseIngredient baseIngredientDtoToBaseIngredient(BaseIngredientDto baseIngredientDto);
    BaseIngredientDto baseIngredientToBaseIngredientDto(BaseIngredient BaseIngredient);
}
