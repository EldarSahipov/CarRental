package com.example.carrental.service;

import com.example.carrental.springsecurity.model.User;
import com.example.carrental.springsecurity.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void add(String email, String password) { // Можно в одном из репо объединить таблицы и добавлять в две сразу
        userRepository.add(email, password);
    }

    public void delete(String email) {
        userRepository.delete(email);
    }

    public void update(String email, String password, long id) {
        userRepository.update(email, password, id);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
