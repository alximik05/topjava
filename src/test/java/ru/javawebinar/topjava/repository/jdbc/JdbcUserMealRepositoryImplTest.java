package ru.javawebinar.topjava.repository.jdbc;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.DbPopulator;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by alximik on 29/12/15.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class JdbcUserMealRepositoryImplTest {

    @Autowired
    UserMealRepository repository;

    @Autowired
    DbPopulator populator;

    @After
    public void setUp() throws Exception {
        populator.execute();
    }

    @Test
    public void testSave() throws Exception {
        UserMeal meal = new UserMeal(LocalDateTime.of(2015, Month.DECEMBER, 30, 12, 0), "Завтрак", 1000);
        repository.save(meal, 100000);
        meal.setId(100008);
        MATCHER.assertEquals(meal,repository.get(100008,100000));
    }

    @Test
    public void testDelete() throws Exception {
        boolean b = repository.delete(100004, 100000);
        Assert.assertEquals(b, true);
        Assert.assertEquals(repository.getAll(100000).size(), 5);
    }

    @Test
    public void testGet() throws Exception {
        MATCHER.assertEquals(breakfeast30,repository.get(100002,100000));
    }

    @Test
    public void testGetAll() throws Exception {
        MEALLIST.add(supper31);
        MEALLIST.add(dinner31);
        MEALLIST.add(breakfeast31);
        MEALLIST.add(supper30);
        MEALLIST.add(dinner30);
        MEALLIST.add(breakfeast30);

        MATCHER.assertCollectionEquals(MEALLIST, repository.getAll(100000));
    }

    @Test
    public void testGetBetween() throws Exception {
       Collection list = repository.getBetween(LocalDateTime.of(2015, Month.MAY, 30, 8, 0), LocalDateTime.of(2015, Month.MAY, 30, 23, 0), 100000);
        Assert.assertEquals(list.size(), 3);
    }
}