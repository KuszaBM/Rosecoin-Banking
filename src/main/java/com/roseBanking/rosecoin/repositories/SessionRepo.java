package com.roseBanking.rosecoin.repositories;

import com.roseBanking.rosecoin.models.Token;
import com.roseBanking.rosecoin.models.User;
import com.roseBanking.rosecoin.models.UserSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SessionRepo {
    private Map<String, UserSession> sessions;

    public SessionRepo() {
        sessions = new HashMap<>();
    }

    public String createNewSession(User user, String connectionIp) {
        Token token = Token.makeRandom(12);
        sessions.put(token.getCode(), new UserSession(user, token, connectionIp));
        return token.getCode();
    }

    public Map<String, UserSession> getSessions() {
        for(String s : sessions.keySet()) {
            System.out.println(s);
        }
        return sessions;
    }
}
