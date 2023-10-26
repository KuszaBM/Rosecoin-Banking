package com.roseBanking.rosecoin.servises;

import com.roseBanking.rosecoin.models.EmailVerifyRequest;
import com.roseBanking.rosecoin.models.Token;
import com.roseBanking.rosecoin.models.User;
import com.roseBanking.rosecoin.repositories.EmailVerifyRequestRepo;
import com.roseBanking.rosecoin.repositories.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailVerifyRequestService {
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final EmailVerifyRequestRepo emailVerifyRequestRepo;
    Logger logger = LoggerFactory.getLogger(EmailVerifyRequestService.class);


    public EmailVerifyRequestService(UserRepo userRepo, EmailVerifyRequestRepo emailVerifyRequestRepo) {
        this.userRepo = userRepo;
        this.emailVerifyRequestRepo = emailVerifyRequestRepo;
    }
    public String verifyUser(String verifyKey) {
        EmailVerifyRequest emailVerifyRequest = emailVerifyRequestRepo.getPendingRequests().get(verifyKey);
        if(emailVerifyRequest != null) {
            User user = userRepo.getUsers().get(emailVerifyRequest.getUsername());
            if(user != null) {
                user.setVerified(true);
                emailVerifyRequestRepo.getPendingRequests().remove(verifyKey);
                logger.info("User successfully verified | user - " + emailVerifyRequest.getUsername());
                return emailVerifyRequest.getUsername();
            }
            logger.info("User not found | user - " + emailVerifyRequest.getUsername());
            return null;
        }
        logger.info("verify key has not been found | key - {}" + verifyKey);
    return null;
    }
    public String addEmailVerifyRequest(String username, String email) {
        Token t = Token.makeRandom(32);
        String verifyCode = t.getCode();
        emailVerifyRequestRepo.addNewRequest(verifyCode, new EmailVerifyRequest(username, email, verifyCode));
        return verifyCode;
    }
}

