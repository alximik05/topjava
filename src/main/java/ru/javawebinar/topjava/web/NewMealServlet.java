package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by USER on 09.12.2015.
 */
public class NewMealServlet extends HttpServlet {
    MealRepository mealRepository;
    @Override
    public void init() {
        mealRepository = new MealRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.valueOf(request.getParameter("id"));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("date"), TimeUtil.DATE_TME_FORMATTER);
        String desc = request.getParameter("description");
        int cal = Integer.valueOf(request.getParameter("number"));
        mealRepository.createOrUpdate(new UserMeal(id, dateTime, desc, cal));
        response.sendRedirect("/meals");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserMeal meal = new UserMeal();
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("newMeal.jsp").forward(request, response);
    }
}
