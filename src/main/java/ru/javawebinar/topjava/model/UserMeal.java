package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal {
    protected final LocalDateTime dateTime;

    protected final String description;

    protected final int calories;

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMeal userMeal = (UserMeal) o;

        if (calories != userMeal.calories) return false;
        if (!dateTime.equals(userMeal.dateTime)) return false;
        return !(description != null ? !description.equals(userMeal.description) : userMeal.description != null);

    }

    @Override
    public int hashCode() {
        int result = dateTime.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + calories;
        return result;
    }
}
