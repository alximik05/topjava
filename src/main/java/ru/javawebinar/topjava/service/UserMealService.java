package ru.javawebinar.topjava.service;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {
    UserMeal save(UserMeal userMeal);

    void delete(int mealId, int useId) throws NotFoundException;

    UserMeal get(int mealId, int userId) throws NotFoundException;

    Collection<UserMeal> getAll(int userId);

    void update(UserMeal userMeal);
}
