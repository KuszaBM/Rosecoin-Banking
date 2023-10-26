package com.roseBanking.rosecoin.responses;

public class NewUserSessionResponse {
    private int status;
    private String token;
    private String username;
    private String ipAddress;

    public NewUserSessionResponse(int status, String username, String ipAddress, String token) {
        this.status = status;
        this.username = username;
        this.ipAddress = ipAddress;
        this.token = token;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
