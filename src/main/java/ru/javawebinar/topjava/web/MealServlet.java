package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    MealRepository mealRepository;
    @Override
    public void init() {
        mealRepository = new MealRepository();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            LOG.info("getAll");
            request.setAttribute("mealList", mealRepository.getAll());
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        }
        else {
            switch (action) {
                case "delete":
                    mealRepository.delete(Integer.valueOf(request.getParameter("id")));
                    response.sendRedirect("meals");
                    break;
                case "edit":
                    request.setAttribute("meal", mealRepository.getById(Integer.valueOf(request.getParameter("id"))));
                    request.getRequestDispatcher("/newMeal.jsp").forward(request, response);
                    break;
                case "new":
                    request.setAttribute("meal", new UserMeal());
                    request.getRequestDispatcher("/newMeal.jsp").forward(request, response);
                    break;
            }
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.valueOf(request.getParameter("id"));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("date"));
        String desc = request.getParameter("description");
        int cal = Integer.valueOf(request.getParameter("calories"));
        UserMeal meal = new UserMeal(id, dateTime, desc, cal);
        mealRepository.createOrUpdate(meal);
        response.sendRedirect("meals");

    }

}

