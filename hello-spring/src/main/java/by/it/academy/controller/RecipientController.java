package by.it.academy.controller;

import by.it.academy.pojo.Recipient;
import by.it.academy.service.RecipientService;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileInputStream;
import java.io.FileOutputStream;

@Controller
public class RecipientController {

    private static final Logger log = LoggerFactory.getLogger(RecipientController.class);

    @Autowired
    RecipientService recipientService;

    @Value("${image.location}")
    String imageLocation;

    @GetMapping("/{id}/recipient.html")
    public ModelAndView showRecipientForm(
            @PathVariable String id,
            ModelAndView modelAndView
    ) {
        Recipient recipient = recipientService.get(id);
        modelAndView.addObject("user", recipient);
        modelAndView.setViewName("recipient");
        return modelAndView;
    }

    @PostMapping("/recipient.html")
    @SneakyThrows
    public String updateRecipient(
            @ModelAttribute Recipient recipient,
            @RequestParam("image") MultipartFile file
            ) {

        byte[] bytes = file.getBytes();
        String fileName = file.getOriginalFilename();
        log.info("File location: " + fileName);
        saveToDisk(bytes, recipient.getId());

        recipientService.update(recipient);
        return "redirect:recipient-list.html";
    }

    @GetMapping("/recipient/{id}/image")
    public @ResponseBody byte[] recipientImage(@PathVariable String id) {
        return readFileFromDisk(id);
    }

    @SneakyThrows
    private void saveToDisk(byte[] bytes, String fileName) {
        FileOutputStream fileOutputStream
                = new FileOutputStream(imageLocation + fileName);
        fileOutputStream.write(bytes);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    @SneakyThrows
    private byte[] readFileFromDisk(String fileName) {
        FileInputStream fileInputStream
                = new FileInputStream(imageLocation + fileName);
        byte[] image = new byte[fileInputStream.available()];
        fileInputStream.read(image);
        fileInputStream.close();
        return image;
    }

}
