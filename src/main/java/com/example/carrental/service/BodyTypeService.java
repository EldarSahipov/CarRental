package com.example.carrental.service;

import com.example.carrental.entity.BodyType;
import com.example.carrental.repo.BodyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodyTypeService {
    private final BodyTypeRepository bodyTypeRepository;

    @Autowired
    public BodyTypeService(BodyTypeRepository bodyTypeRepository) {
        this.bodyTypeRepository = bodyTypeRepository;
    }

    public void add(String name) {
        bodyTypeRepository.add(name);
    }

    public void delete(short id) {
        bodyTypeRepository.delete(id);
    }

    public void update(String name, short id) {
        bodyTypeRepository.update(name, id);
    }

    public List<BodyType> getAll() {
        return bodyTypeRepository.getAll();
    }
}
