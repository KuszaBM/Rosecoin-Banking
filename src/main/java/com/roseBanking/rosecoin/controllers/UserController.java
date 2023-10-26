package com.roseBanking.rosecoin.controllers;

import com.roseBanking.rosecoin.models.User;
import com.roseBanking.rosecoin.servises.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    protected Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
    @PostMapping("/newUser")
    public HttpStatus addNewUser(@RequestBody User newUser) {
        if(service.addNewUser(newUser) == 1) {
            return HttpStatus.ACCEPTED;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }
    @PostMapping("/updateCoins")
    public float updateCoins(@RequestBody User user, @RequestParam(name = "coins") int coins) {
        logger.info("trying to add coins to {} - coins {}", user.getUsername(), coins);
        service.addCoinsToUser(user, coins);
        return 1;
    }
}
