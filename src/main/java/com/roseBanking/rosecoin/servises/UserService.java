package com.roseBanking.rosecoin.servises;

import com.roseBanking.rosecoin.models.User;
import com.roseBanking.rosecoin.repositories.UserRepo;
import com.roseBanking.rosecoin.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final EmailService emailService;
    @Autowired
    private final EmailVerifyRequestService emailVerifyRequestService;

    protected Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepo userRepo, UserRepository userRepository, EmailService emailService, EmailVerifyRequestService emailVerifyRequestService) {
        this.userRepo = userRepo;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.emailVerifyRequestService = emailVerifyRequestService;
    }

    public int registerUser(User user) {
        if(user.getEmail() == null) {
            logger.info("email is mandatory field in user");
            return -1;
        }
        addNewUser(user);
        String verifyCode = emailVerifyRequestService.addEmailVerifyRequest(user.getUsername(), user.getEmail());
        String verifyUrl = "http://192.168.122.22:8080/base/" + verifyCode + "/verifyEmail";
        emailService.sendMessage(user.getEmail(), "Welcome new user",
                "Hello "
                        + user.getUsername()
                        + ", You are now one of many happy users. Enter this link to verify your account - "
                        + verifyUrl);
        return 1;
    }

    public int addNewUser(User newUser) {
        userRepository.save(newUser);
        return userRepo.addUser(newUser);
    }
    public boolean verifyUser(User user) {
        User u = userRepo.getUsers().get(user.getUsername());
        if(u == null)
            return false;
        if(u.verifyUser(user))
            return true;
        return false;
    }
    public List<User> getAllVerifiedUsers() {
        //.filter(User::isVerified)
        userRepository.findAll();
        return userRepo.getUsers().values().stream().collect(Collectors.toList());
    }

    public void addCoinsToUser(User user, int coins) {
        User u = userRepo.getUsers().get(user.getUsername());
        u.setCoins(user.getCoins() + coins);
        logger.info("new coins value for {} - {}", u.getUsername(), u.getCoins());
    }
}
