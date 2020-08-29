package by.it.academy.controller;

import by.it.academy.pojo.Recipient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/new-recipient.html")
public class NewRecipientController {

    @GetMapping
    public String showNewRecipient() {
        return "new-recipient";
    }

    @PostMapping
    public String createNewRecipient(
            @ModelAttribute Recipient recipient
    ) {
        System.out.println("New recipient: " + recipient);
        return "redirect:home.html";
    }

}
