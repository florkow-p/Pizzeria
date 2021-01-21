package pl.pizzeria.meal.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.pizzeria.meal.domain.Meal;
import pl.pizzeria.meal.domain.MealDto;

import java.util.List;

@Mapper
public interface MealMapper {
    MealMapper INSTANCE = Mappers.getMapper(MealMapper.class);

    Meal mealDtoToMeal(MealDto mealDto);
    MealDto mealToMealDto(Meal Meal);
    List<MealDto> mealListToMealDtoList(List<Meal> meals);
}
