package com.roseBanking.rosecoin.models;

public class UserBaseInfoResponse {
    private String username;
    private int coins;

    public UserBaseInfoResponse(User user) {
        this.username = user.getUsername();
        this.coins = user.getCoins();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
