package com.example.carrental.service;

import com.example.carrental.entity.BrandCar;
import com.example.carrental.repo.BrandCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandCarService {
    private final BrandCarRepository brandCarRepository;

    @Autowired
    public BrandCarService(BrandCarRepository brandCarRepository) {
        this.brandCarRepository = brandCarRepository;
    }

    public void add(String name) {
        brandCarRepository.add(name);
    }

    public void delete(int id) {
        brandCarRepository.delete(id);
    }

    public void update(String name, int id) {
        brandCarRepository.update(name, id);
    }

    public List<BrandCar> getAll() {
        return brandCarRepository.getAll();
    }

    public BrandCar getBrandCarByName(String name) {
        return brandCarRepository.getBrandCarByName(name);
    }
}
