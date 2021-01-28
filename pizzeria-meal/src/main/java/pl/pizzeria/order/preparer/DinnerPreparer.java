package pl.pizzeria.order.preparer;

import org.springframework.stereotype.Service;
import pl.pizzeria.meal.domain.Meal;
import pl.pizzeria.meal.domain.MealDto;
import pl.pizzeria.meal.domain.MealType;
import pl.pizzeria.meal.domain.dinner.*;
import pl.pizzeria.meal.domain.mapper.BaseIngredientMapper;
import pl.pizzeria.meal.domain.mapper.DinnerMapper;
import pl.pizzeria.meal.domain.mapper.ExtrasMapper;
import pl.pizzeria.order.domain.MealRequest;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class DinnerPreparer implements MealPreparer {

    public static final String INVALID_EXTRAS_ID = "Invalid extras id";
    public static final String INVALID_BASE_INGREDIENT_ID = "Invalid base ingredient id";

    @Override
    public MealDto prepare(Meal meal, MealRequest mealRequest, List<Meal> menu) {
        MealDto mealDto = DinnerMapper.INSTANCE.dinnerToDinnerDto((Dinner) meal);
        prepareDinner(mealDto, mealRequest, menu);
        return mealDto;
    }

    private void prepareDinner(MealDto mealDto, MealRequest mealRequest, List<Meal> menu) {
        if(isBaseIngredientAllowed(mealDto)) {
            addBaseIngredient(mealDto, mealRequest, menu);
        }

        if (mealRequest.getExtrasId() != null) {
            addExtras(mealDto, mealRequest, menu);
        }
    }

    private boolean isBaseIngredientAllowed(MealDto mealDto) {
        return ((DinnerDto) mealDto).isAllowedBaseIngredient();
    }

    private void addBaseIngredient(MealDto mealDto, MealRequest mealRequest, List<Meal> menu) {
        if(mealRequest.getBaseIngredientId() == null) {
            throw new IllegalArgumentException("baseIngredientId for "
                    + mealDto.getName().toUpperCase(Locale.ROOT) + " cannot be null");
        }

        Meal ingredient = getMealById(mealRequest.getBaseIngredientId(), menu)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_BASE_INGREDIENT_ID));

        if(!MealType.BASE_INGREDIENT.equals(ingredient.getMealType())) {
            throw new IllegalArgumentException(INVALID_BASE_INGREDIENT_ID);
        }

        BaseIngredientDto ingredientDto =
                BaseIngredientMapper.INSTANCE.baseIngredientToBaseIngredientDto((BaseIngredient) ingredient);

        ((DinnerDto) mealDto).setBaseIngredient(ingredientDto);
    }

    private void addExtras(MealDto mealDto, MealRequest mealRequest, List<Meal> menu) {
        Meal extras = getMealById(mealRequest.getExtrasId(), menu)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_EXTRAS_ID));

        if(!MealType.EXTRAS.equals(extras.getMealType())) {
            throw new IllegalArgumentException(INVALID_EXTRAS_ID);
        }

        ExtrasDto extrasDto = ExtrasMapper.INSTANCE.extrasToExtrasDto((Extras) extras);

        ((DinnerDto) mealDto).setExtras(extrasDto);
    }

    private Optional<Meal> getMealById(Long mealId, List<Meal> menu) {
        return menu.stream()
                .filter(item -> item.getId().equals(mealId))
                .findFirst();
    }
}
