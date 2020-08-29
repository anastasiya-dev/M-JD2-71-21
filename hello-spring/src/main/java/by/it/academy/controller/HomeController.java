package by.it.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(value = "/home.html", method = RequestMethod.GET)
    public ModelAndView homePage(ModelAndView modelAndView) {
        System.out.println("Call homePage");



        modelAndView.addObject("greeting", "I love Spring and Summer!");
        modelAndView.setViewName("home"); // -> /WEB-INF/jsp/ + home + .jsp


        return modelAndView;
    }
}
