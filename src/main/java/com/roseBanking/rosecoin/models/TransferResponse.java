package com.roseBanking.rosecoin.models;

public class TransferResponse {
    private int responseCode;
    private int coins;
    public TransferResponse(int responseCode, int coins) {
        this.responseCode = responseCode;
        this.coins = coins;
    }

    public TransferResponse() {
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
