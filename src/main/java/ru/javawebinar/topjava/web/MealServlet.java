package ru.javawebinar.topjava.web;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    private UserMealService service;
    private LoggedUser loggedUser;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try(ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            service = context.getBean(UserMealService.class);
        }
     //   service = new InMemoryUserMealRepositoryImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        UserMeal userMeal = new UserMeal(id.isEmpty() ? null : Integer.valueOf(id),
                loggedUser.getId(),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
        LOG.info(userMeal.isNew() ? "Create {}" : "Update {}", userMeal);
        service.save(userMeal);
        response.sendRedirect("meals");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (loggedUser == null) {
            String logged = request.getParameter("userId");
            if (logged != null) {
                loggedUser = new LoggedUser(Integer.valueOf(logged));
                request.getSession().setAttribute("logged",loggedUser);
            }
            else request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        int userId = loggedUser.getId();
        request.getSession().getAttribute("logged");
        LOG.info("user with id {} logged in", userId);
        String action = request.getParameter("action");
        if (action == null) {
            LOG.info("getAll");
            request.setAttribute("mealList",
                    UserMealsUtil.getWithExceeded(service.getAll(userId), UserMealsUtil.DEFAULT_CALORIES_PER_DAY));
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            service.delete(id, loggedUser.getId());
            response.sendRedirect("meals");
        } else {
            final UserMeal meal = action.equals("create") ?
                    new UserMeal(loggedUser.getId(),LocalDateTime.now(), "", loggedUser.getCaloriesPerDay()) :
                    service.get(getId(request),loggedUser.getId());
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealEdit.jsp").forward(request, response);
        }

    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
