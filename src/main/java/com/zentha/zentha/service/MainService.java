package com.zentha.zentha.service;

import com.zentha.zentha.entity.User;
import com.zentha.zentha.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MainService {
    private final UserRepository userRepository;

    public MainService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getMessage() {
        return "Zentha";
    }

    public Integer getScore(User user) {
        return user.getScore();
    }

    public void setScore(User user, Integer score) {
        user.setScore(score);
    }

    public Optional<User> getUser(Long uid) {
        return userRepository.findById(uid);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
