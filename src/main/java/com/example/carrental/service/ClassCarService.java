package com.example.carrental.service;

import com.example.carrental.entity.ClassCar;
import com.example.carrental.repo.ClassCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassCarService {
    private final ClassCarRepository classCarRepository;

    @Autowired
    public ClassCarService(ClassCarRepository classCarRepository) {
        this.classCarRepository = classCarRepository;
    }

    public void add(String name) {
        classCarRepository.add(name);
    }

    public void delete(short id) {
        classCarRepository.delete(id);
    }

    public void update(String name, short id) {
        classCarRepository.update(name, id);
    }

    public List<ClassCar> getAll() {
        return classCarRepository.getAll();
    }
}
