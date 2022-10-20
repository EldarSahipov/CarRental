package com.example.carrental.service;

import com.example.carrental.entity.CityCar;
import com.example.carrental.repo.CityCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityCarService {
    private final CityCarRepository cityCarRepository;

    @Autowired
    public CityCarService(CityCarRepository cityCarRepository) {
        this.cityCarRepository = cityCarRepository;
    }

    public void add(String name) {
        cityCarRepository.add(name);
    }

    public void delete(int id) {
        cityCarRepository.delete(id);
    }

    public void update(String name, int id) {
        cityCarRepository.update(name, id);
    }

    public List<CityCar> getAll() {
        return cityCarRepository.getAll();
    }
}
