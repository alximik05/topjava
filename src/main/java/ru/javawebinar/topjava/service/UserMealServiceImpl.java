package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {
    public static Logger LOG = LoggerFactory.getLogger(UserMealServiceImpl.class);

    @Autowired
    private UserMealRepository repository;

    @Override
    public UserMeal save(UserMeal userMeal) {
        LOG.debug("from service - save {}", userMeal);
        return repository.save(userMeal);
    }

    @Override
    public void delete(int mealId, int userId) throws NotFoundException {
        LOG.debug("from service - delete {}", mealId);
        repository.delete(mealId, userId);
    }

    @Override
    public UserMeal get(int mealId, int userId) throws NotFoundException {
        LOG.debug("from service - get {}", mealId);
        return repository.get(mealId, userId);
    }

    @Override
    public Collection<UserMeal> getAll(int userId) {
        LOG.debug("from service - getAll");
        return repository.getAll(userId);
    }

    @Override
    public void update(UserMeal userMeal) {
        LOG.debug("from service - update {}",userMeal);
        repository.save(userMeal);
    }
}
