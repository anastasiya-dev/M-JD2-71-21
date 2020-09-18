package by.it.academy.controller;

import by.it.academy.pojo.Recipient;
import by.it.academy.service.RecipientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/new-recipient.html")
public class NewRecipientController {

    private static final Logger log = LoggerFactory.getLogger(NewRecipientController.class);

    @Autowired
    RecipientService recipientService;

    @GetMapping
    public String showNewRecipient() {
        return "new-recipient";
    }

    @PostMapping
    public String createNewRecipient(
            @ModelAttribute Recipient recipient,
            Model model
    ) {
        log.info("New recipient: " + recipient);
        if (recipientService.createNewRecipient(recipient)) {
            return "redirect:home.html";
        } else {
            model.addAttribute("errorMessage", "Cannot create a new recipient");
            return "error-page";
        }
    }

}
