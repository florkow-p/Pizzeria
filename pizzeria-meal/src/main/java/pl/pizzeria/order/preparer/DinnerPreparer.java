package pl.pizzeria.order.preparer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pizzeria.meal.domain.Meal;
import pl.pizzeria.meal.domain.MealDto;
import pl.pizzeria.meal.domain.dinner.BaseIngredient;
import pl.pizzeria.meal.domain.dinner.Dinner;
import pl.pizzeria.meal.domain.dinner.DinnerDto;
import pl.pizzeria.meal.domain.dinner.Extras;
import pl.pizzeria.meal.domain.mapper.DinnerMapper;
import pl.pizzeria.meal.web.MealServiceImpl;
import pl.pizzeria.order.domain.MealRequest;

import java.util.Locale;

@RequiredArgsConstructor
@Service
public class DinnerPreparer implements MealPreparer {

    private final MealServiceImpl mealService;

    @Override
    public MealDto prepare(Meal meal, MealRequest mealRequest) {
        MealDto mealDto = DinnerMapper.INSTANCE.dinnerToDinnerDto((Dinner) meal);
        prepareDinner(mealDto, mealRequest);
        return mealDto;
    }

    private void prepareDinner(MealDto mealDto, MealRequest mealRequest) {
        if(isBaseIngredientAllowed(mealDto)) {
            addBaseIngredient(mealDto, mealRequest);
        }

        if (mealRequest.getExtrasId() != null) {
            addExtras(mealDto, mealRequest);
        }
    }

    private boolean isBaseIngredientAllowed(MealDto mealDto) {
        return ((DinnerDto) mealDto).isAllowedBaseIngredient();
    }

    private void addBaseIngredient(MealDto mealDto, MealRequest mealRequest) {
        if(mealRequest.getBaseIngredientId() == null) {
            throw new IllegalArgumentException("baseIngredientId for "
                    + mealDto.getName().toUpperCase(Locale.ROOT) + " cannot be null");
        }

        Meal ingredient = mealService.findById(mealRequest.getBaseIngredientId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid base ingredient id"));

        ((DinnerDto) mealDto).setBaseIngredient((BaseIngredient) ingredient);
    }

    private void addExtras(MealDto mealDto, MealRequest mealRequest) {
        Meal extras = mealService.findById(mealRequest.getExtrasId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid extras id"));

        ((DinnerDto) mealDto).setExtras((Extras) extras);
    }
}
