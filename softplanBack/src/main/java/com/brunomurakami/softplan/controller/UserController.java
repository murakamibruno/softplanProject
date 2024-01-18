package com.brunomurakami.softplan.controller;

import com.brunomurakami.softplan.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validateLogin")
@Slf4j
public class UserController {

    @GetMapping(produces = "application/json")
    public User validateLogin() {
        return new User("User successfully authenticated");
    }
}
