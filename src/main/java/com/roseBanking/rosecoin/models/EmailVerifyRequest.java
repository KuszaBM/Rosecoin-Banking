package com.roseBanking.rosecoin.models;

public class EmailVerifyRequest {
    private String username;
    private String email;
    private String verifyKey;

    public EmailVerifyRequest(String username, String email, String verifyKey) {
        this.username = username;
        this.email = email;
        this.verifyKey = verifyKey;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getVerifyKey() {
        return verifyKey;
    }
}
