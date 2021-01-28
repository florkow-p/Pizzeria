package pl.pizzeria.order.preparer;

import org.junit.jupiter.api.Test;
import pl.pizzeria.meal.domain.Meal;
import pl.pizzeria.meal.domain.MealType;
import pl.pizzeria.meal.domain.dinner.BaseIngredient;
import pl.pizzeria.meal.domain.dinner.Dinner;
import pl.pizzeria.meal.domain.dinner.DinnerDto;
import pl.pizzeria.meal.domain.dinner.Extras;
import pl.pizzeria.order.domain.MealRequest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DinnerPreparerTest {
    private final DinnerPreparer dinnerPreparer = new DinnerPreparer();

    private Meal dinner;
    private MealRequest dinnerMealRequest;
    private List<Meal> extrasAndIngredients;

    @Test
    public void prepareValidDinner_AllowedBaseIngredientAsTrue() {
        dinnerWithIngredientInit();

        DinnerDto dinnerDto = (DinnerDto) dinnerPreparer.prepare(dinner, dinnerMealRequest, extrasAndIngredients);

        assertEquals(dinnerDto.getBaseIngredient().getName(), "base ingredient");
        assertEquals(dinnerDto.getExtras().getName(), "valid extras");
        assertEquals(dinnerDto.getName(), "name");
        assertTrue(dinnerDto.isAllowedBaseIngredient());
    }

    @Test
    public void prepareValidDinner_AllowedBaseIngredientAsFalse() {
        dinnerWithoutIngredientInit();

        DinnerDto dinnerDto = (DinnerDto) dinnerPreparer.prepare(dinner, dinnerMealRequest, extrasAndIngredients);

        assertFalse(dinnerDto.isAllowedBaseIngredient());
        assertNull(dinnerDto.getBaseIngredient());
        assertNotNull(dinnerDto.getExtras());
    }

    @Test
    public void invalidTopping_ShouldThrowException() {
        invalidDinnerInit();

        assertThrows(IllegalArgumentException.class, () ->
                dinnerPreparer.prepare(dinner, dinnerMealRequest, extrasAndIngredients));
    }

    private void invalidDinnerInit() {
        buildValidDinnerWithIngredient();
        buildDinnerMealRequest(getInvalidExtras(), getValidIngredient());
    }

    private void dinnerWithIngredientInit() {
        buildValidDinnerWithIngredient();
        buildDinnerMealRequest(getValidExtras(), getValidIngredient());
    }

    private void dinnerWithoutIngredientInit() {
        buildValidDinnerWithoutIngredient();
        buildDinnerMealRequest(getValidExtras(), getValidIngredient());
    }

    private BaseIngredient getValidIngredient() {
        return BaseIngredient.builder()
                .id(2L)
                .name("base ingredient")
                .price(BigDecimal.valueOf(5))
                .mealType(MealType.BASE_INGREDIENT)
                .build();
    }

    private Extras getValidExtras() {
        return Extras.builder()
                .id(3L)
                .name("valid extras")
                .price(BigDecimal.valueOf(5))
                .mealType(MealType.EXTRAS)
                .build();
    }

    private Extras getInvalidExtras() {
        return Extras.builder()
                .id(4L)
                .name("invalid extras")
                .price(BigDecimal.valueOf(5))
                .mealType(MealType.DINNER)
                .build();
    }

    private void buildValidDinnerWithIngredient() {
        this.dinner = Dinner.builder()
                .id(1L)
                .name("name")
                .price(BigDecimal.valueOf(10))
                .mealType(MealType.DINNER)
                .allowedBaseIngredient(true)
                .build();
    }

    private void buildValidDinnerWithoutIngredient() {
        this.dinner = Dinner.builder()
                .id(1L)
                .name("name")
                .price(BigDecimal.valueOf(10))
                .mealType(MealType.DINNER)
                .allowedBaseIngredient(false)
                .build();
    }

    private void buildDinnerMealRequest(Extras extras, BaseIngredient baseIngredient) {
        extrasAndIngredients = List.of(extras, baseIngredient);
        this.dinnerMealRequest = new MealRequest(1L,
                2,
                null,
                extras.getId(),
                baseIngredient.getId());
    }
}