package pl.pizzeria.meal.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pizzeria.meal.domain.Meal;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final MealRepository repository;

    @Override
    public Optional<Meal> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Meal> findAll() {
        return repository.findAll();
    }

    @Override
    public Meal save(Meal meal) {
        return repository.save(meal);
    }

    @Override
    public List<Meal> findByIdIn(List<Long> ids) {
        return repository.findByIdIn(ids);
    }
}
