package com.roseBanking.rosecoin.controllers;

import com.roseBanking.rosecoin.models.TransferRequest;
import com.roseBanking.rosecoin.models.TransferResponse;
import com.roseBanking.rosecoin.models.User;
import com.roseBanking.rosecoin.models.UserBaseInfoResponse;
import com.roseBanking.rosecoin.servises.EmailService;
import com.roseBanking.rosecoin.servises.SessionService;
import com.roseBanking.rosecoin.servises.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserInterfaceController {
    @Autowired
    private final SessionService sessionService;
    @Autowired
    private final EmailService emailService;
    @Autowired
    private final UserService userService;
    protected Logger logger = LoggerFactory.getLogger(UserInterfaceController.class);

    public UserInterfaceController(SessionService sessionService, EmailService emailService, UserService userService) {
        this.sessionService = sessionService;
        this.emailService = emailService;
        this.userService = userService;
    }

    @CrossOrigin
    @GetMapping("session/{token}/sayHello")
    public String sayHello(@PathVariable(name = "token") String token) {
        User user = sessionService.getSession(token).getUser();
        return "hello - " + user.getUsername() + ", You have " + user.getCoins() + " Rose Coins";
    }
    @CrossOrigin
    @PostMapping("session/{token}/sendTo")
    @ResponseBody
    public TransferResponse sendCoins(@RequestBody TransferRequest transferRequest,@PathVariable(name = "token") String token) {
        logger.info("new transfer Req - | to: {} | coins: {}", transferRequest.getUsername(), transferRequest.getCoins());
        return sessionService.transferCoins(token, transferRequest.getUsername(), transferRequest.getCoins());
    }
    @CrossOrigin
    @GetMapping("session/{token}/getUserBaseData")
    public UserBaseInfoResponse getUserBaseData(@PathVariable(name = "token") String token, HttpServletRequest request) {
        if(!sessionService.isTrustedRequest(token, request.getRemoteAddr())) {
            logger.warn("ip not right");
            return null;
        }
        return sessionService.sessionOwnerBaseData(token);
    }
    @CrossOrigin
    @GetMapping("session/{token}/getUsers")
    public List<String> listUsers(@PathVariable(name = "token") String token, HttpServletRequest request) {
        logger.warn("getUsers call");
        if(!sessionService.isTrustedRequest(token, request.getRemoteAddr())) {
            logger.warn("ip not right");
            return null;
        }
        List<String> a =userService.getAllVerifiedUsers().stream().filter(u -> {
            if(u.getUsername().equals(sessionService.getSession(token).getUser().getUsername()))
                return false;
            return true;
        }).map(User::getUsername).toList();
        for (String s : a) {
            logger.info("- {}", s);
        }

        return a;
    }
}
