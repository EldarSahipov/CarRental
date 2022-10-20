package com.example.carrental.service;

import com.example.carrental.entity.Tenant;
import com.example.carrental.repo.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService {
    private final TenantRepository tenantRepository;

    @Autowired
    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public void add(String phoneNumber, String firstName, String lastName, short yearDriving) {
        tenantRepository.add(phoneNumber, firstName, lastName, yearDriving);
    }

    public void delete(String phoneNumber) {
        tenantRepository.delete(phoneNumber);
    }

    public void update(String firstName, String lastName, short yearDriving, String phoneNumber) {
        tenantRepository.update(firstName, lastName, yearDriving, phoneNumber);
    }

    public List<Tenant> getAll() {
        return tenantRepository.getAll();
    }
}
