package by.it.academy.rest;

import by.it.academy.pojo.Recipient;
import by.it.academy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipientRestController {

    private static final Logger log = LoggerFactory.getLogger(RecipientRestController.class);

    @Autowired
    UserService userService;

    @GetMapping("/recipients")
    public ResponseEntity recipients() {
        final List all = userService.getAll();

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");

        return new ResponseEntity(all, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/recipients/{id}")
    public ResponseEntity createRecipient(@RequestBody Recipient recipient) {
        log.info("Call createRecipient");
        boolean result = userService.createNewRecipient(recipient);

        return new ResponseEntity(recipient, result ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/recipients/{id}")
    public ResponseEntity updateRecipient(
            @RequestBody Recipient recipient,
            @PathVariable String id) {
        log.info("Call updateRecipient");
        final Recipient savedRecipient = userService.get(id);
        if (savedRecipient == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else if (savedRecipient.equals(recipient)) {
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        userService.update(recipient);
        return new ResponseEntity(recipient, HttpStatus.OK);
    }

}
