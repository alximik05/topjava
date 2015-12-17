package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.service.UserMealServiceImpl;
import ru.javawebinar.topjava.service.UserService;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {
    public static Logger LOG = LoggerFactory.getLogger(UserMealRestController.class);
//    @Autowired
//    private UserMealService service;
//
//    public List<UserMeal> getAll() {
//        LOG.info("getAll");
//        return (List<UserMeal>) service.getAll(LoggedUser.id());
//    }
//
//    public UserMeal get(int mealId) {
//        LOG.info("get " + mealId);
//        return service.get(mealId, LoggedUser.id());
//    }
//
//    public UserMeal create(UserMeal meal) {
//        LOG.info("create {}" + meal);
//        return service.save(meal);
//    }
//
//    public void delete(int id) {
//        LOG.info("delete " + id);
//        service.delete(id, LoggedUser.id());
//    }
//
//    public void update(UserMeal userMeal) {
//        LOG.info("update " + userMeal);
//        service.update(userMeal);
//    }
}
