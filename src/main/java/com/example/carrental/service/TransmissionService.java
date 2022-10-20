package com.example.carrental.service;

import com.example.carrental.entity.Transmission;
import com.example.carrental.repo.TransmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransmissionService {
    private final TransmissionRepository transmissionRepository;

    @Autowired
    public TransmissionService(TransmissionRepository transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }

    public void add(String name) {
        transmissionRepository.add(name);
    }

    public void delete(short id) {
        transmissionRepository.delete(id);
    }

    public void update(String name, short id) {
        transmissionRepository.update(name, id);
    }

    public List<Transmission> getAll() {
        return transmissionRepository.getAll();
    }
}
