package com.roseBanking.rosecoin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    private boolean isVerified = false;
    private int coins;
    @Transient
    private UserSession userSession;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(String username) {
        this.username = username;
        this.password = null;
    }
    public User() {}

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
    public boolean hasActiveUserSession() {
        return this.userSession != null;
    }

    public String activeSessionIp() {
        if(hasActiveUserSession())
            return userSession.getConnectionIp();
        return null;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public boolean verifyUser(User user) {
        if(user.getPassword() == null)
            return false;
        if(user.getUsername().equals(username) && user.getPassword().equals(password))
            return true;
        return false;
    }
}
