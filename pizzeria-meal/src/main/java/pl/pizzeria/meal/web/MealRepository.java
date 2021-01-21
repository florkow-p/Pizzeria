package pl.pizzeria.meal.web;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pizzeria.meal.domain.Meal;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByIdIn(List<Long> ids);
}
