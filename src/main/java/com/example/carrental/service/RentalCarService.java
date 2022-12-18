package com.example.carrental.service;

import com.example.carrental.entity.RentalCar;
import com.example.carrental.repo.RentalCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RentalCarService {
    private final RentalCarRepository rentalCarRepository;

    @Autowired
    public RentalCarService(RentalCarRepository rentalCarRepository) {
        this.rentalCarRepository = rentalCarRepository;
    }

    public void add(String phoneNumber, Date endLease, Date startLease, long carId, int price) {
        rentalCarRepository.add(phoneNumber, endLease, startLease, carId, price);
    }

    public void delete(long idRentalCar) {
        rentalCarRepository.delete(idRentalCar);
    }

    public void update(long idRentalCar, Date endLease, Date startLease, int price) {
        rentalCarRepository.update(idRentalCar, endLease, startLease, price);
    }

    public List<RentalCar> getAll() {
        return rentalCarRepository.getAll();
    }

    public Integer getProfitForThePeriod(Date startLease, Date endLease) {
        return rentalCarRepository.getProfitForThePeriod(startLease, endLease);
    }
}
