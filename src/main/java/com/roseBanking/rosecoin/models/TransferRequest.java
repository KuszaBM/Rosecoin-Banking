package com.roseBanking.rosecoin.models;

public class TransferRequest {
    private String username;
    private int coins;
    public TransferRequest(String username, int coins) {
        this.username = username;
        this.coins = coins;
    }

    public TransferRequest() {
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
