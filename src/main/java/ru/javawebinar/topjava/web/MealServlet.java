package ru.javawebinar.topjava.web;

import com.sun.deploy.net.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by USER on 03.12.2015.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        LOG.debug("doGet -> redirect");
        List<UserMealWithExceed> list = UserMealsUtil.getListForTest();
        req.setAttribute("mealList", list);
        req.getRequestDispatcher("mealList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.debug("doPost -> ");
        String desc = req.getParameter("description");
        String dateTime = req.getParameter("dateTime");
        String call = req.getParameter("calories");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
        UserMeal userMeal = new UserMeal(localDateTime, desc, Integer.parseInt(call));
        UserMealsUtil.create(userMeal);

    }
}
