package by.it.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = "/home.html", method = RequestMethod.GET)
    public String homePage(Model modelAndView) {
        System.out.println("Call homePage");
        // -> /WEB-INF/jsp/ + home + .jsp
        modelAndView.addAttribute("greeting", "I love Spring and Summer!");
        return "home";
    }
}
