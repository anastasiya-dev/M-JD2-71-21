package by.it.academy.controller;

import by.it.academy.pojo.Recipient;
import by.it.academy.service.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RecipientListController {

    @Autowired
    RecipientService recipientService;

    @GetMapping("/recipient-list.html")
    public ModelAndView recipientList(ModelAndView modelAndView) {
        List<Recipient> users = recipientService.getAll();
        modelAndView.setViewName("recipient-list");
        modelAndView.addObject("users", users);
        return modelAndView;
    }
}
