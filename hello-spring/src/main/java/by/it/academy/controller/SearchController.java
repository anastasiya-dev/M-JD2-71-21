package by.it.academy.controller;

import by.it.academy.service.RecipientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {

    private static final Logger log = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    RecipientService recipientService;

    @GetMapping("/search.html")
    @Secured("ROLE_USER")
    public ModelAndView search(
            @RequestParam String search,
            ModelAndView modelAndView) {
        log.info("Search: " + search);

        modelAndView.addObject("users", recipientService.search(search));
        modelAndView.setViewName("search-result");
        return modelAndView;
    }

}
