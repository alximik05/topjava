package ru.javawebinar.topjava.web;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.web.meal.UserMealRestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
@Controller
public class RootController {
    @Autowired
    private UserService service;

    @Autowired
    private UserMealRestController controller;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("userList", service.getAll());
        return "userList";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        LoggedUser.setId(userId);
        return "redirect:meals";
    }

//    @RequestMapping(value = "/meals", method = RequestMethod.GET)
//    public String mealList(Model model) {
//        model.addAttribute("mealList", controller.getAll());
//        return "mealList";
//    }
//
//    @RequestMapping(value = "/meals/delete", method = RequestMethod.GET)
//    public String deleteMeal(@RequestParam(name = "id") int id) {
//        controller.delete(id);
//        return "redirect:/meals";
//    }
//
//    @RequestMapping(value = "/meals/update", method = RequestMethod.GET)
//    public String updateMeal(@RequestParam(name = "id") int id, Model model) {
//        model.addAttribute("meal", controller.get(id));
//        return "mealEdit";
//    }
//
//    @RequestMapping(value = "/meals/update**}", method = RequestMethod.POST)
//    public String updateMealPost(HttpServletRequest request) {
//        UserMeal userMeal = new UserMeal(Integer.valueOf(request.getParameter("id")),
//                LocalDateTime.parse(request.getParameter("dateTime")),
//                request.getParameter("description"),
//                Integer.valueOf(request.getParameter("calories")));
//        controller.update(userMeal);
//        return "redirect:/meals";
//    }
}
