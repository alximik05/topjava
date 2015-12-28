package ru.javawebinar.topjava.repository.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.repository.UserMealRepository;

import static org.junit.Assert.*;

/**
 * Created by alximik on 27/12/15.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class JdbcUserRepositoryImplTest {

    @Autowired
    UserMealRepository repository;

    @Test
    public void testGet() throws Exception {

        System.out.println(repository.get(100015, 100000).toString());
    }
}