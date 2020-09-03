package by.it.academy.rest;

import by.it.academy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipientRestController {

    @Autowired
    UserService userService;

    @GetMapping("/recipients")
    public ResponseEntity recipients() {
        final List all = userService.getAll();

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");

        return new ResponseEntity(all, httpHeaders, HttpStatus.OK);
    }

}
