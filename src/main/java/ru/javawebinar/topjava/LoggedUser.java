package ru.javawebinar.topjava;

import ru.javawebinar.topjava.util.UserMealsUtil;

/**
 * GKislin
 * 06.03.2015.
 */
public class LoggedUser {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCaloriesPerDay() {
        return UserMealsUtil.DEFAULT_CALORIES_PER_DAY;
    }

    public LoggedUser(int id) {
        this.id = id;
    }
}
