package com.roseBanking.rosecoin.controllers;

import com.roseBanking.rosecoin.models.User;
import com.roseBanking.rosecoin.responses.NewUserSessionResponse;
import com.roseBanking.rosecoin.servises.EmailVerifyRequestService;
import com.roseBanking.rosecoin.servises.SessionService;
import com.roseBanking.rosecoin.servises.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BaseController {
    private static final String notOk = "not Ok";
    @Autowired
    private final UserService userService;
    @Autowired
    private final SessionService sessionService;
    @Autowired
    private final EmailVerifyRequestService emailVerifyRequestService;
    Logger logger = LoggerFactory.getLogger(BaseController.class);

    public BaseController(UserService userService, SessionService sessionService, EmailVerifyRequestService emailVerifyRequestService) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.emailVerifyRequestService = emailVerifyRequestService;
    }

    @CrossOrigin
    @PostMapping("base/logIn")
    @ResponseBody
    public NewUserSessionResponse logIn(@RequestBody User user, HttpServletRequest request) {
        logger.info("New login request for user [{}] from IP: {}", user.getUsername(), request.getRemoteAddr());
        if (userService.verifyUser(user)) {
            return  sessionService.newSession(user, request.getRemoteAddr());
        }
        return new NewUserSessionResponse(201, user.getUsername(), request.getRemoteAddr(), null);
    }
    @CrossOrigin
    @GetMapping("base/{verifyKey}/verifyEmail")
    public String verifyEmail(@PathVariable(name = "verifyKey") String verifyKey) {
        String name = emailVerifyRequestService.verifyUser(verifyKey);
        return name != null ? "verify ok for user - " + name : "verify not ok";
    }
    @CrossOrigin
    @GetMapping("base/getVerifiedUsers")
    public List<User> getVerifiedUsers() {
        return userService.getAllVerifiedUsers();
    }
    @CrossOrigin
    @PostMapping("base/register")
    public String register(@RequestBody User user) {
        logger.info("d12 p 41");
        if(userService.registerUser(user) == 1) {
            return "new user added";
        }
        return "bad request";
    }


}
