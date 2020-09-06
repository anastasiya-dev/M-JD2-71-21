package by.it.academy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/home.html", method = RequestMethod.GET)
    public ModelAndView homePage(ModelAndView modelAndView) {
        log.info("Call homePage");

        modelAndView.addObject("greeting", "I love Spring and Summer!");
        modelAndView.setViewName("home"); // -> /WEB-INF/jsp/ + home + .jsp

        return modelAndView;
    }
}
