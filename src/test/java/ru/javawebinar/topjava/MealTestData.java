package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

            public static UserMeal breakfeast30 = new UserMeal(100002,LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
            public static UserMeal dinner30 = new UserMeal(100003,LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
            public static UserMeal supper30 = new UserMeal(100004,LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
            public static UserMeal breakfeast31 = new UserMeal(100005,LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
            public static UserMeal dinner31 = new UserMeal(100006,LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
            public static UserMeal supper31 = new UserMeal(100007,LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);

    public static Collection<UserMeal> MEALLIST = new ArrayList<>();

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

}
