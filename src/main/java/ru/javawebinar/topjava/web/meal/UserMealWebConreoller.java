package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.model.UserMeal;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * Created by alximik on 16/01/16.
 */

@Controller
@RequestMapping(value = "/meals")
public class UserMealWebConreoller {

    @Autowired
    UserMealRestController controller;


    @RequestMapping
    public String mealList(Model model) {
        model.addAttribute("mealList", controller.getAll());
        return "mealList";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteMeal(@RequestParam(name = "id") int id) {
        controller.delete(id);
        return "redirect:/meals";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateMeal(@RequestParam(name = "id") int id, Model model) {
        model.addAttribute("meal", controller.get(id));
        return "mealEdit";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateMealPost(HttpServletRequest request) {
        UserMeal userMeal = new UserMeal(Integer.valueOf(request.getParameter("id")),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
        controller.update(userMeal);
        return "redirect:/meals";
    }
}
