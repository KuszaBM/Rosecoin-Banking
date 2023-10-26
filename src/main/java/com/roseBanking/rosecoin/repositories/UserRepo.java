package com.roseBanking.rosecoin.repositories;

import com.roseBanking.rosecoin.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepo {

    @Autowired
    private final UserRepository userRepository;
    private Map<String, User> users;
    public UserRepo(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.users = new HashMap<>();
    }

    public Map<String, User> getUsers() {
        return users;
    }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).get(0);
    }
    public int addUser(User user) {
        if(users.get(user.getUsername()) != null) {
            return -1;
        }
        users.put(user.getUsername(), user);
        return 1;
    }
    public int removeUser(User user) {
        if(users.get(user.getUsername()) == null) {
            return -1;
        }
        users.remove(user.getUsername());
        return 1;
    }
    public int updateCoins(User user, int coins) {
        User active = users.get(user.getUsername());
        if(active == null) {
            return -1;
        }
        active.setCoins(active.getCoins() + coins);
        return 1;
    }
}
