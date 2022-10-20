package com.example.carrental.service;

import com.example.carrental.entity.User;
import com.example.carrental.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
