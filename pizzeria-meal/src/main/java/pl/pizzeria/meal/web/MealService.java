package pl.pizzeria.meal.web;

import pl.pizzeria.meal.domain.Meal;

import java.util.List;
import java.util.Optional;

public interface MealService {
    Optional<Meal> findById(Long id);
    List<Meal> findAll();
    Meal save(Meal meal);
    List<Meal> findByIdIn(List<Long> ids);
}
