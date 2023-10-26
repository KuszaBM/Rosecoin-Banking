package com.roseBanking.rosecoin.models;


public class UserSession {
    private final User user;
    private final Token token;
    private final String connectionIp;

    public UserSession(User user, Token token, String connectionIp) {
        this.user = user;
        this.token = token;
        this.connectionIp = connectionIp;
    }

    public User getUser() {
        return user;
    }

    public String getConnectionIp() {
        return connectionIp;
    }
    public boolean isIpAddressCorrect(String ipAddress) {
        return connectionIp.equals(ipAddress);
    }
}
