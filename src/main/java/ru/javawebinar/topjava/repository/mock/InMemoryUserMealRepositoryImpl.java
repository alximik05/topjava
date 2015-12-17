package ru.javawebinar.topjava.repository.mock;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    public static Logger LOG = LoggerFactory.getLogger(InMemoryUserMealRepositoryImpl.class);
    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(this::save);
    }

    @Override
    public UserMeal save(UserMeal userMeal) {
        LOG.info("from repository save {}" , userMeal);
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        return repository.put(userMeal.getId(), userMeal);
    }

    @Override
    public boolean delete(int mealId, int userId) {
        LOG.info("from repository delete {}" + mealId);
        return repository.remove(mealId) != null;
    }

    @Override
    public UserMeal get(int mealId, int userId) {
        LOG.info("from repository get {}" + mealId);
        return repository.get(mealId);
    }

    @Override
    public Collection<UserMeal> getAll(int userId) {
        LOG.info("from repository getAll");
        Collection<UserMeal> result = repository.values().stream().filter(meal -> meal.getUserId() == userId).collect(Collectors.toCollection(ArrayList::new));
        return result;
    }
}

