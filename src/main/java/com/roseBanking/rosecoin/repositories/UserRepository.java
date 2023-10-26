package com.roseBanking.rosecoin.repositories;

import com.roseBanking.rosecoin.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
}
