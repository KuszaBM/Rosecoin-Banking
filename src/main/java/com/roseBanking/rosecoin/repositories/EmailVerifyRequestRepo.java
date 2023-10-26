package com.roseBanking.rosecoin.repositories;

import com.roseBanking.rosecoin.models.EmailVerifyRequest;
import com.roseBanking.rosecoin.models.Token;
import com.roseBanking.rosecoin.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class EmailVerifyRequestRepo {
    private Map<String, EmailVerifyRequest> pendingRequests;

    public EmailVerifyRequestRepo() {
        pendingRequests = new HashMap<>();
    }

    public Map<String, EmailVerifyRequest> getPendingRequests() {
        return pendingRequests;
    }
    public void addNewRequest(String verifyCode, EmailVerifyRequest emailVerifyRequest) {
        pendingRequests.putIfAbsent(verifyCode, emailVerifyRequest);
    }
}
