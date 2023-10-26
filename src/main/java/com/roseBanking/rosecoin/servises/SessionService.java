package com.roseBanking.rosecoin.servises;

import com.roseBanking.rosecoin.models.*;
import com.roseBanking.rosecoin.repositories.SessionRepo;
import com.roseBanking.rosecoin.repositories.UserRepo;
import com.roseBanking.rosecoin.responses.NewUserSessionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Autowired
    private final SessionRepo sessionRepo;
    @Autowired
    private final UserRepo userRepo;
    Logger logger = LoggerFactory.getLogger(SessionService.class);
    public SessionService(SessionRepo sessionRepo, UserRepo userRepo) {
        this.sessionRepo = sessionRepo;
        this.userRepo = userRepo;
    }
    public boolean isTrustedRequest(String token, String requestIpAddress) {
        UserSession session =  sessionRepo.getSessions().get(token);
        if(session.isIpAddressCorrect(requestIpAddress)) {
            return true;
        } else {
            logger.warn("Request from different ip address | Session Ip: {} | User Ip: {}", session.getConnectionIp(), requestIpAddress);
            return false;
        }
    }

    public NewUserSessionResponse newSession(User user, String ipAddress) {
        if(userRepo.getUsers().get(user.getUsername()).hasActiveUserSession()) {
            logger.info("User [{}] have active session from IP : {}", user.getUsername(), user.activeSessionIp());
        }
        return new NewUserSessionResponse(101, null, null, sessionRepo.createNewSession(userRepo.getUsers().get(user.getUsername()), ipAddress));
    }
    public UserSession getSession(String token) {
        return sessionRepo.getSessions().get(token);
    }

    public UserBaseInfoResponse sessionOwnerBaseData(String token) {
        return new UserBaseInfoResponse(sessionRepo.getSessions().get(token).getUser());
    }

    public TransferResponse transferCoins(String token, String acceptor, int amount) {
        System.out.println("reg - " + token);
        User u1 = userRepo.getUsers().get(sessionRepo.getSessions().get(token).getUser().getUsername());
        User u2 = userRepo.getUsers().get(acceptor);
        u1.setCoins(u1.getCoins() - amount);
        u2.setCoins(u2.getCoins() + amount);
        return new TransferResponse(101, u1.getCoins());
    }


}
