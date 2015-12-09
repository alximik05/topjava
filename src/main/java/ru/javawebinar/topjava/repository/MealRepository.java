package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by USER on 09.12.2015.
 */
public class MealRepository {
    private static Map<Integer, UserMeal> mealMap = new HashMap<>();
    private static AtomicInteger newId = new AtomicInteger(100);

    public MealRepository() {
        createOrUpdate(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        createOrUpdate(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        createOrUpdate(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        createOrUpdate(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        createOrUpdate(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        createOrUpdate(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    public void createOrUpdate(UserMeal meal) {
        if (meal.getId() != 0) {
            mealMap.put(meal.getId(), meal);
        } else {
            meal.setId(newId.incrementAndGet());
            mealMap.put(meal.getId(), meal);
        }
    }

    public UserMeal getById(int id) {
        return mealMap.get(id);
    }

    public void delete(int id) {
        mealMap.remove(id);
    }

    public List<UserMealWithExceed> getAll() {
       return UserMealsUtil.getFilteredWithExceededByCycle(mealMap.values(), LocalTime.MIN, LocalTime.MAX, 2000);
    }

}
